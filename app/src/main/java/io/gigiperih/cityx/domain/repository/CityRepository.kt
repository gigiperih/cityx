package io.gigiperih.cityx.domain.repository

import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.structure.Trie

interface CityRepository {
    fun getList(): List<City>?

    fun getTrie(): Trie
}