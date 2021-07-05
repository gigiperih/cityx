package io.gigiperih.cityx.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.gigiperih.cityx.R


class CityActivity : AppCompatActivity(R.layout.activity_city) {
    companion object {
        const val KEY_LAT = "lat"
        const val KEY_LON = "lon"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // update from splash
        setTheme(R.style.Theme_CityX)

        super.onCreate(savedInstanceState)
    }
}