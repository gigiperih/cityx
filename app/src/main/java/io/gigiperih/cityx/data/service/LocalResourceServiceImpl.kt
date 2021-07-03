package io.gigiperih.cityx.data.service

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.gigiperih.cityx.data.City

class LocalResourceServiceImpl : LocalResourceService {
    override fun get(file: String): List<City>? {
        return try {
            val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            val listType = Types.newParameterizedType(List::class.java, City::class.java)
            val adapter = moshi.adapter<List<City>>(listType)

            // return sorted
            this::class.java.classLoader?.getResource(file)?.readText()
                ?.let { adapter.fromJson(it) }
        } catch (e: JsonDataException) {
            null
        }
    }
}