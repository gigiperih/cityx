package io.gigiperih.cityx.data.repository

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.source.LocalResourceService
import io.gigiperih.cityx.domain.repository.CityRepository

class CityRepositoryImpl(private val localResourceService: LocalResourceService) : CityRepository {
    override fun get(fileName: String): List<City>? {
        return try {
            val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            val listType = Types.newParameterizedType(List::class.java, City::class.java)

            val adapter = moshi.adapter<List<City>>(listType)

            localResourceService.get(fileName = fileName)?.let { adapter.fromJson(it) }
        } catch (e: JsonDataException) {
            null
        }
    }
}