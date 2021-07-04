package io.gigiperih.cityx.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.gigiperih.cityx.R
import org.koin.androidx.viewmodel.ext.android.viewModel


class CityActivity : AppCompatActivity() {
    private val viewModel: CityViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
    }
}