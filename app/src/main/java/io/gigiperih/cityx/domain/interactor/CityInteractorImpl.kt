package io.gigiperih.cityx.domain.interactor

import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.mapper.traverse
import io.gigiperih.cityx.domain.mapper.ResultState
import io.gigiperih.cityx.domain.repository.CityRepository
import io.gigiperih.cityx.utils.dispatcher.DefaultDispatcherProvider
import io.gigiperih.cityx.utils.dispatcher.DispatcherProvider
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CityInteractorImpl(
    private val repository: CityRepository,
    private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) : CityInteractor {
    override fun search(keywords: String?, page: Int) =
        flow<ResultState<List<City>>> {
            // init as loading state
            emit(ResultState.OnLoading())

            // perform query
            val result = if (keywords.isNullOrEmpty()) {
                repository.getList()
            } else {
                repository
                    .getTrie()
                    .filterPrefix(keywords)
                    .traverse()
            }

            // checking result
            if (!result.isNullOrEmpty()) {
                emit(
                    ResultState.OnSuccess(
                        result.chunked(10)[page - 1], "Found ${result.size} cities."
                    )
                )
            } else {
                emit(ResultState.OnError("Result not found"))
            }
        }.flowOn(dispatchers.main())
}