package io.gigiperih.cityx.domain.repository

import io.gigiperih.cityx.data.City

interface CityRepository {
    fun get(file: String): HashMap<String, MutableList<City>>
}