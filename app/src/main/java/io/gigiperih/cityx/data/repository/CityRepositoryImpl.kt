package io.gigiperih.cityx.data.repository

import io.gigiperih.cityx.data.service.LocalResourceService
import io.gigiperih.cityx.data.structure.Trie
import io.gigiperih.cityx.domain.repository.CityRepository
import io.gigiperih.cityx.utils.dispatcher.DefaultDispatcherProvider
import io.gigiperih.cityx.utils.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext

/**
 * this layer will be very helpful if we have multiple data sources
 */
class CityRepositoryImpl(
    private val localResourceService: LocalResourceService,
    private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) : CityRepository {
    override suspend fun getTrie(): Trie {
        return withContext(dispatchers.io()) {
            return@withContext localResourceService.fetchResource()
        }

    }
}