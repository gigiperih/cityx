package io.gigiperih.cityx.data.service

import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.structure.Trie

interface LocalResourceService {
    fun getList(): List<City>?

    fun getTrie(): Trie
}