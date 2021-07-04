package io.gigiperih.cityx.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.domain.interactor.CityInteractor
import io.gigiperih.cityx.domain.mapper.ResultState
import io.gigiperih.cityx.utils.dispatcher.DefaultDispatcherProvider
import io.gigiperih.cityx.utils.dispatcher.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Will be shared through activity lifecycle
 * for both (CitiesFragment and CityFragment)
 */
class CityViewModel(
    private val interactor: CityInteractor,
    private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) : ViewModel() {
    private val _resultState = MutableStateFlow<ResultState<List<City>>>(ResultState.OnLoading())
    val resultState: StateFlow<ResultState<List<City>>> = _resultState

    init {
        search(keywords = "", page = 1)
    }

    fun search(keywords: String, page: Int) {
        viewModelScope.launch(dispatchers.main()) {
            interactor.search(keywords = keywords, page = page).collect {
                _resultState.value = it
            }
        }
    }
}