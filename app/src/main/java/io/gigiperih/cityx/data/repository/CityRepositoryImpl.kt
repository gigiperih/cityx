package io.gigiperih.cityx.data.repository

import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.Trie
import io.gigiperih.cityx.data.service.LocalResourceService
import io.gigiperih.cityx.domain.repository.CityRepository

class CityRepositoryImpl(private val localResourceService: LocalResourceService) : CityRepository {
    override fun get(file: String): HashMap<String, MutableList<City>> {
        val citiesFromService = localResourceService.get(file)

        // TODO refactor
        // map (keywords, cities)
        val map = hashMapOf<String, MutableList<City>>()
        citiesFromService?.let { cities ->
            var words = ""
            // map keywords to cities for O(~1) filtering time complexity
//            cities.forEach { city ->
//                for (i in city.name.indices) {
//                    words += city.name[i].lowercaseChar()
//                    if (map[words] != null) {
//                        map[words]?.add(city)
//                    } else {
//                        map[words] = mutableListOf(city)
//                    }
//                }
//            }
            // provide default map for empty keywords
            map[""] = cities.toMutableList()
        }

        return map
    }

    override fun getTrie() = Trie<Char>()
}