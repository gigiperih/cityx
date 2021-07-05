package io.gigiperih.cityx.presentation.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.gigiperih.cityx.R
import io.gigiperih.cityx.domain.mapper.ResultState
import io.gigiperih.cityx.presentation.CityActivity
import io.gigiperih.cityx.presentation.CityViewModel
import io.gigiperih.cityx.utils.extensions.gone
import io.gigiperih.cityx.utils.extensions.textChanges
import io.gigiperih.cityx.utils.extensions.visible
import kotlinx.android.synthetic.main.fragment_cities.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

@FlowPreview
@ExperimentalCoroutinesApi
class CitiesFragment : Fragment() {
    private val viewModel: CityViewModel by viewModel()
    private var citiesAdapter: CitiesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_cities,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        updateUi()
    }

    private fun init() {
        setupAdapter()
        setupSearch()
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(requireActivity())
        recycler_view_cities.layoutManager = layoutManager
        citiesAdapter = CitiesAdapter { city ->
            val nav = findNavController()
            nav.navigate(
                R.id.action_citiesFragment_to_cityFragment,
                bundleOf(
                    CityActivity.KEY_LAT to city.coord.lat,
                    CityActivity.KEY_LON to city.coord.lon
                )
            )
        }
        recycler_view_cities.adapter = citiesAdapter
    }

    private fun setupSearch() {
        text_input_search.textChanges()
            .filterNot { it.isNullOrEmpty() }
            .debounce(666)
            .onEach {
                viewModel.search(it.toString())
            }
            .launchIn(lifecycleScope)
    }

    private fun updateUi() {
        viewModel.resultState.observe(requireActivity(), { resultState ->
            when (resultState) {
                is ResultState.OnLoading -> {
                    text_information.text = getString(R.string.fetching)
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