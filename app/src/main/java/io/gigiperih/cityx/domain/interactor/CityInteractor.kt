package io.gigiperih.cityx.domain.interactor

import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.domain.mapper.ResultState
import kotlinx.coroutines.flow.Flow

interface CityInteractor {
    suspend fun search(keywords: String? = "", page: Int = 1): Flow<ResultState<List<City>>>
}