package io.gigiperih.cityx.domain.interactor

import io.gigiperih.cityx.data.City

interface CityInteractor {
    suspend fun search(keywords: String? = "", page: Int = 1): List<City>?
}