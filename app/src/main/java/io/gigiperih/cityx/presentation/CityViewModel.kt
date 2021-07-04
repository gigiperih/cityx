package io.gigiperih.cityx.presentation

import io.gigiperih.cityx.domain.interactor.CityInteractor

/**
 * Will be shared through activity lifecycle
 * for both fragments (CitiesFragment and CityFragment)
 */
class CityViewModel(private val interactor: CityInteractor) {

}