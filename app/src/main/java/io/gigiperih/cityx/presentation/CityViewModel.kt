package io.gigiperih.cityx.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.domain.interactor.CityInteractor
import io.gigiperih.cityx.domain.mapper.ResultState
import io.gigiperih.cityx.utils.dispatcher.DefaultDispatcherProvider
import io.gigiperih.cityx.utils.dispatcher.DispatcherProvider
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Will be shared through activity lifecycle
 * for both (CitiesFragment and CityFragment)
 */
class CityViewModel(
    private val interactor: CityInteractor,
    private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) : ViewModel() {
    val resultLiveData: MutableLiveData<ResultState<List<City>>> = MutableLiveData()

    fun search(keywords: String, page: Int) {
        viewModelScope.launch {
            withContext(dispatchers.main()) {
                interactor.search(keywords = keywords, page = page).onEach { resultState ->
                    resultLiveData.postValue(resultState)
                }
            }
        }
    }
}