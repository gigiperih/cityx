package io.gigiperih.cityx.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.gigiperih.cityx.R


class CityActivity : AppCompatActivity(R.layout.activity_city) {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Make sure this is before calling super.onCreate
        setTheme(R.style.Theme_CityX)
        super.onCreate(savedInstanceState)
    }
}