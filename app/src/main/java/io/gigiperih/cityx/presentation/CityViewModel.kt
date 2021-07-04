package io.gigiperih.cityx.presentation

import androidx.lifecycle.LiveData
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

/**
 * Will be shared through activity lifecycle
 * for both (CitiesFragment and CityFragment)
 */
class CityViewModel(
    private val interactor: CityInteractor,
    private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) : ViewModel() {
    private val _resultState = MutableLiveData<ResultState<List<City>>>(ResultState.OnLoading())
    val resultState: LiveData<ResultState<List<City>>> = _resultState

    fun get() {
        _resultState.postValue(ResultState.OnError("Eewww"))

//        viewModelScope.launch {
//            interactor.search("", 1).onEach {
//                _resultState.postValue(it)
//            }
//        }
    }
}