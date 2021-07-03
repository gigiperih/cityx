package io.gigiperih.cityx.data.repository

import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.service.LocalResourceService
import io.gigiperih.cityx.data.structure.Trie
import io.gigiperih.cityx.domain.repository.CityRepository

class CityRepositoryImpl(
    private val localResourceService: LocalResourceService
) : CityRepository {
    override fun get(page: Int): List<City>? {
        return localResourceService.getList()
    }

    override fun search(keywords: String?, page: Int): Trie {
        return localResourceService.getTrie()
    }
}