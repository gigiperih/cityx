package io.gigiperih.cityx.domain.interactor

import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.domain.mapper.ResultState
import kotlinx.coroutines.flow.Flow

interface CityInteractor {
    fun search(keywords: String? = ""): Flow<ResultState<List<City>>>
}