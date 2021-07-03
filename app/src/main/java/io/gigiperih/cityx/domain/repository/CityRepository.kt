package io.gigiperih.cityx.domain.repository

import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.structure.Trie

interface CityRepository {
    fun get(page: Int): List<City>?

    fun search(keywords: String?, page: Int): Trie
}