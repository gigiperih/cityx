package io.gigiperih.cityx.data.service

import io.gigiperih.cityx.data.City

interface LocalResourceService {
    fun get(file: String): List<City>?
}