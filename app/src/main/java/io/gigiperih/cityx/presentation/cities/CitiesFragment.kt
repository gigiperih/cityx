package io.gigiperih.cityx.presentation.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.gigiperih.cityx.R
import io.gigiperih.cityx.domain.mapper.ResultState
import io.gigiperih.cityx.presentation.CityViewModel
import io.gigiperih.cityx.utils.extensions.gone
import io.gigiperih.cityx.utils.extensions.visible
import kotlinx.android.synthetic.main.fragment_cities.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CitiesFragment : Fragment() {
    private val viewModel: CityViewModel by viewModel()
    private var citiesAdapter: CitiesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "A lot of cities"

        init()
        updateUi()
    }

    private fun init() {
        val layoutManager = LinearLayoutManager(requireActivity())
        recycler_view_cities.layoutManager = layoutManager
        citiesAdapter = CitiesAdapter()
        recycler_view_cities.adapter = citiesAdapter
    }

    private fun updateUi() {
        viewModel.resultState.observe(requireActivity(), { resultState ->
            when (resultState) {
                is ResultState.OnLoading -> {
                    progress_circular.visible()
                }
                is ResultState.OnSuccess -> {
                    progress_circular.gone()

                    text_information.text = resultState.message
                    resultState.data?.let { cities ->
                        citiesAdapter?.addAll(cities)
                    }
                }
                is ResultState.OnError -> {
                    progress_circular.gone()
                    text_information.text = resultState.message
                }
            }
        })
        viewModel.get()
    }
}