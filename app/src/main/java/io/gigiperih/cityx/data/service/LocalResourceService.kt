package io.gigiperih.cityx.data.service

import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.structure.Trie

interface LocalResourceService {
    suspend fun getList(): List<City>?

    suspend fun getTrie(): Trie
}