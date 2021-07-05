package io.gigiperih.cityx.presentation.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import io.gigiperih.cityx.R
import io.gigiperih.cityx.domain.mapper.ResultState
import io.gigiperih.cityx.presentation.CityViewModel
import io.gigiperih.cityx.utils.extensions.gone
import io.gigiperih.cityx.utils.extensions.textChanges
import io.gigiperih.cityx.utils.extensions.visible
import kotlinx.android.synthetic.main.fragment_cities.*
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class CitiesFragment : Fragment() {
    private val viewModel: CityViewModel by viewModel()
    private var citiesAdapter: CitiesAdapter? = null

    private var searchQuery = "Bandong"

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
        citiesAdapter = CitiesAdapter { city ->
            Timber.d("selected_city $city")
        }
        recycler_view_cities.adapter = citiesAdapter

        text_input_search.textChanges()
            .filterNot { it.isNullOrEmpty() }
            .debounce(1000)
            .onEach {
                viewModel.search(it.toString())
            }
            .launchIn(lifecycleScope)
    }

    private fun updateUi() {
        viewModel.resultState.observe(requireActivity(), { resultState ->
            when (resultState) {
                is ResultState.OnLoading -> {
                    text_information.text = "Fetching..."
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

                    citiesAdapter?.clear()
                }
            }
        })
    }
}