package io.gigiperih.cityx

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private val stringCity: MutableLiveData<String> = MutableLiveData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val validSample = """
        [
            {"country":"UA","name":"Hurzuf","_id":707860,"coord":{"lon":34.283333,"lat":44.549999}},
            {"country":"RU","name":"Novinki","_id":519188,"coord":{"lon":37.666668,"lat":55.683334}},
            {"country":"NP","name":"Gorkhā","_id":1283378,"coord":{"lon":84.633331,"lat":28}},
            {"country":"IN","name":"State of Haryāna","_id":1270260,"coord":{"lon":76,"lat":29}},
            {"country":"UA","name":"Holubynka","_id":708546,"coord":{"lon":33.900002,"lat":44.599998}},
            {"country":"NP","name":"Bāgmatī Zone","_id":1283710,"coord":{"lon":85.416664,"lat":28}},
            {"country":"RU","name":"Mar’ina Roshcha","_id":529334,"coord":{"lon":37.611111,"lat":55.796391}},
            {"country":"IN","name":"Republic of India","_id":1269750,"coord":{"lon":77,"lat":20}},
            {"country":"NP","name":"Kathmandu","_id":1283240,"coord":{"lon":85.316666,"lat":27.716667}},
            {"country":"UA","name":"Laspi","_id":703363,"coord":{"lon":33.733334,"lat":44.416668}},
            {"country":"VE","name":"Merida","_id":3632308,"coord":{"lon":-71.144997,"lat":8.598333}}
        ]
         """

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val listType = Types.newParameterizedType(List::class.java, City::class.java)

        val adapter = moshi.adapter<List<City>>(listType)

        val data = adapter.fromJson(validSample)

        print()
    }

    fun print() {
        val result = liveData {
            emit(getStuff())
        }

        result.observe(this, {
            text_view.text = "$it"
            Log.d("kememdikna", it)
        })
    }

    suspend fun getStuff(): String {
        return withContext(Dispatchers.Main) {
            applicationContext.resources.openRawResource(R.raw.cities).bufferedReader()
                .use { it.readText() }
        }
    }
}

data class City(val country: String, val name: String, val _id: Long, val coord: Coordinate)
data class Coordinate(val lat: Double, val lon: Double)