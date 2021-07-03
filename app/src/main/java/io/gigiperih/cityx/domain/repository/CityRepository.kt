package io.gigiperih.cityx.domain.repository

import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.Trie

interface CityRepository {
    fun get(file: String): HashMap<String, MutableList<City>>

    fun getTrie(): Trie
}