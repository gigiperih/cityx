package io.gigiperih.cityx.domain.repository

import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.structure.Trie

interface CityRepository {
    fun get(file: String): List<City>?

    fun buildTrie(cities: List<City>?): Trie
}