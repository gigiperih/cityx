package io.gigiperih.cityx.domain.repository

import io.gigiperih.cityx.data.City

interface CityRepository {
    fun get(keywords: String): HashMap<String, List<City>?>
}