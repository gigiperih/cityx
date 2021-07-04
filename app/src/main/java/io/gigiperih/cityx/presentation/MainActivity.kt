package io.gigiperih.cityx.presentation

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import io.gigiperih.cityx.R
import io.gigiperih.cityx.domain.mapper.ResultState
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val viewModel: CityViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.resultState.observe(this, { resultState ->
            when (resultState) {
                is ResultState.OnLoading -> {
                    text_view.text = "Loading"
                }
                is ResultState.OnSuccess -> {
                    text_view.text = "Success: ${resultState.message} "
                }
                is ResultState.OnError -> {
                    text_view.text = "Error: ${resultState.message}"
                }
            }
        })
        viewModel.get()
    }
}