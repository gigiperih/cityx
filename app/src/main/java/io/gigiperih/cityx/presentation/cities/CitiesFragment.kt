package io.gigiperih.cityx.presentation.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.gigiperih.cityx.R
import io.gigiperih.cityx.domain.mapper.ResultState
import io.gigiperih.cityx.presentation.CityViewModel
import kotlinx.android.synthetic.main.fragment_cities.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CitiesFragment : Fragment() {
    private val viewModel: CityViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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