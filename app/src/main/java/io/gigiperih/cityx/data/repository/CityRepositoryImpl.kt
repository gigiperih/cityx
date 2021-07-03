package io.gigiperih.cityx.data.repository

import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.Trie
import io.gigiperih.cityx.data.mapper.sortAlphabetically
import io.gigiperih.cityx.data.service.LocalResourceService
import io.gigiperih.cityx.domain.repository.CityRepository

class CityRepositoryImpl(private val localResourceService: LocalResourceService) : CityRepository {
    override fun get(file: String): List<City>? {
        return localResourceService.get(file)?.sortAlphabetically()
    }

    override fun getTrie(cities: List<City>?): Trie {
        val trie = Trie()
        cities?.forEach {
            trie.insert("${it.name} ${it.country}".lowercase(), it)
        }

        return trie
    }
}