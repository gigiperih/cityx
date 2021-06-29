package io.gigiperih.cityx

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonEncodingException
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Test

class CityRepositoryTest {
    @Test
    fun `given valid json file, when parsing is succeed, should return correct list of cities`() {
        val result = loadData(FakeData.validSample)

        assertThat(result).apply {
            isEqualTo(FakeData.expectedSample)
            hasSize(2)
            containsNoDuplicates()
        }
    }

    @Test
    fun `given valid but incomplete json file, when parsing is failing, should return null`() {
        val result = loadData(FakeData.incompleteSample)

        assertThat(result).apply {
            isNull()
        }
    }

    @Test(expected = JsonEncodingException::class)
    fun `given invalid json file, when parsing is failing, should throws JsonEncodingException`() {
        loadData(FakeData.invalidSample)
    }

    @Test
    fun `given unsorted list of city, when sorting is success, should return sorted list`() {
        val unsortedList = loadData(FakeData.unsortedSample)
        val result = unsortedList.sortAlphabetically()

        assertThat(result).apply {
            isEqualTo(FakeData.expectedSample)
        }
    }

    @Test
    fun `given null value of city, when sorting is failing, should return null`() {
        val unsortedList = loadData(FakeData.incompleteSample)
        val result = unsortedList.sortAlphabetically()

        assertThat(result).apply {
            isNull()
        }
    }

    // TODO refactor
    private fun loadData(json: String): List<City>? {
        return try {
            val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            val listType = Types.newParameterizedType(List::class.java, City::class.java)

            val adapter = moshi.adapter<List<City>>(listType)

            adapter.fromJson(json)
        } catch (e: JsonDataException) {
            null
        }
    }

    // TODO refactor (might use faster algorithm?)
    private fun List<City>?.sortAlphabetically(): List<City>? {
        if (this == null) return null
        return this.sortedBy { it.name }
    }
}