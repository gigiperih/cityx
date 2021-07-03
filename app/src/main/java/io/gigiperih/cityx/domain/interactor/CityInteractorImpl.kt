package io.gigiperih.cityx.domain.interactor

import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.mapper.traverse
import io.gigiperih.cityx.domain.repository.CityRepository

class CityInteractorImpl(private val repository: CityRepository) : CityInteractor {
    override fun get(page: Int): List<City>? {
        return repository.get(page)
    }

    override fun search(keywords: String?, page: Int): List<City>? {
        // return result
        return if (keywords.isNullOrEmpty()) {
            repository.get(page)
        } else {
            repository.search(keywords, page).filterPrefix(keywords).traverse()
        }
    }
}