package io.gigiperih.cityx.domain.interactor

import io.gigiperih.cityx.data.City

interface CityInteractor {
    fun get(page: Int = 1): List<City>?

    fun search(keywords: String?, page: Int): List<City>?
}