package io.gigiperih.cityx.data.repository

import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.source.LocalResourceService
import io.gigiperih.cityx.domain.repository.CityRepository

class CityRepositoryImpl(private val localResourceService: LocalResourceService) : CityRepository {
    override fun get(keywords: String): HashMap<String, List<City>?> {
        // providing hashmap for better runtime search
        // TODO: provide real hashing implementation
        val list = localResourceService.get(file = "cities_2.json")
        return hashMapOf("" to list)
    }
}