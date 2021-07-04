package io.gigiperih.cityx.data.repository

import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.service.LocalResourceService
import io.gigiperih.cityx.data.structure.Trie
import io.gigiperih.cityx.domain.repository.CityRepository

/**
 * this layer will be very helpful if we have multiple data sources
 */
class CityRepositoryImpl(
    private val localResourceService: LocalResourceService
) : CityRepository {
    override fun getList(): List<City>? {
        return localResourceService.getList()
    }

    override fun getTrie(): Trie {
        return localResourceService.getTrie()
    }
}