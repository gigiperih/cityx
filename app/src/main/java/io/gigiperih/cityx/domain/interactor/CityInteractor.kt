package io.gigiperih.cityx.domain.interactor

import io.gigiperih.cityx.data.City

interface CityInteractor {
    fun search(keywords: String? = "", page: Int = 1): List<City>?
}