package io.gigiperih.cityx.data.repository

import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.mapper.CityMapper
import io.gigiperih.cityx.data.service.LocalResourceService
import io.gigiperih.cityx.data.structure.Trie
import io.gigiperih.cityx.domain.repository.CityRepository

class CityRepositoryImpl(
    private val localResourceService: LocalResourceService,
    private val mapper: CityMapper
) : CityRepository {
    override fun get(file: String): List<City>? {
        return mapper.sortAlphabetically(localResourceService.get(file))
    }

    override fun buildTrie(cities: List<City>?): Trie {
        val trie = Trie()
        cities?.forEach {
            trie.insert("${it.name} ${it.country}".lowercase(), it)
        }

        return trie
    }
}