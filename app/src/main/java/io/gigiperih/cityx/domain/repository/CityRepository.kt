package io.gigiperih.cityx.domain.repository

import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.structure.Trie

interface CityRepository {
    suspend fun getTrie(): Trie
}