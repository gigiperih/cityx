package io.gigiperih.cityx.data.source

import io.gigiperih.cityx.data.City

interface LocalResourceService {
    fun get(file: String): List<City>?
}