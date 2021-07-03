package io.gigiperih.cityx.domain.interactor

import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.mapper.traverse
import io.gigiperih.cityx.domain.repository.CityRepository

class CityInteractorImpl(private val repository: CityRepository) : CityInteractor {
    override fun search(keywords: String?, page: Int): List<City>? {
        return if (keywords.isNullOrEmpty()) {
            repository.getList()?.chunked(10)?.get(page - 1)
        } else {
            repository
                .getTrie()
                .filterPrefix(keywords)
                .traverse()
                .apply {
                    if (this.isNotEmpty()) chunked(10)[page - 1]
                }
        }
    }
}