package io.gigiperih.cityx.data.mapper

import io.gigiperih.cityx.data.City

object CityMapper {
    fun List<City>?.toHashMap(): HashMap<String, City>? {
        if (this == null) return null

        val hash = hashMapOf<String, City>()
        this.map {
            // map city + country as hash key
            hash["${it.name} ${it.country}"] = it
        }
        return hash
    }
}