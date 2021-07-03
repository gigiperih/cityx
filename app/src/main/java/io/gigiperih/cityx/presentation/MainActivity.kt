package io.gigiperih.cityx.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import io.gigiperih.cityx.R


class MainActivity : AppCompatActivity() {
    private val stringCity: MutableLiveData<String> = MutableLiveData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}