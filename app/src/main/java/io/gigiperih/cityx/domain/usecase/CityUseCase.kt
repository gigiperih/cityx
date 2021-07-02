package io.gigiperih.cityx.domain.usecase

import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.domain.repository.CityRepository

class CityUseCase(private val repository: CityRepository) {
    private var cities: List<City>? = null

    init {
        cities = repository.get("jazon")
    }

    fun get(): List<City>? {
        // put in hash table as cache
        return repository.get("jazon")
    }

    fun search(query: String): List<City>? {
        return null
    }
}