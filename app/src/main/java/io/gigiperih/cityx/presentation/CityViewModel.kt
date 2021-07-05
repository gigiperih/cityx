package io.gigiperih.cityx.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.domain.interactor.CityInteractor
import io.gigiperih.cityx.domain.mapper.ResultState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class CityViewModel(
    val interactor: CityInteractor
) : ViewModel() {
    private val _resultState = MutableLiveData<ResultState<List<City>>>()
    val resultState: LiveData<ResultState<List<City>>> = _resultState

    init {
        search("")
    }

    fun search(keywords: String) {
        viewModelScope.launch {
            interactor.search(keywords, 1).collectLatest {
                Timber.d("kememmmmmms $it")
                _resultState.postValue(it)
            }
        }
    }
}