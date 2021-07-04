package io.gigiperih.cityx.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.domain.interactor.CityInteractor
import io.gigiperih.cityx.domain.mapper.ResultState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Will be shared through activity lifecycle
 * for both (CitiesFragment and CityFragment)
 */
class CityViewModel(
    val interactor: CityInteractor
) : ViewModel() {
    private val _resultState = MutableLiveData<ResultState<List<City>>>(ResultState.OnLoading())
    val resultState: LiveData<ResultState<List<City>>> = _resultState

    init {
        _resultState.postValue(ResultState.OnError("Eewww"))

    }

    fun get() {
        viewModelScope.launch {
            interactor.search("", 1).collect {
                Timber.d("kememmmmmms $it")
                _resultState.postValue(it)
            }
        }
    }
}