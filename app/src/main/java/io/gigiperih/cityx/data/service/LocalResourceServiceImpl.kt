package io.gigiperih.cityx.data.service

import android.content.Context
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.gigiperih.cityx.R
import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.mapper.sortAlphabetically
import io.gigiperih.cityx.data.structure.Trie
import io.gigiperih.cityx.utils.dispatcher.DefaultDispatcherProvider
import io.gigiperih.cityx.utils.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext

class LocalResourceServiceImpl(
    private val context: Context,
    private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) : LocalResourceService {
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val listType = Types.newParameterizedType(List::class.java, City::class.java)
    private val adapter = moshi.adapter<List<City>>(listType)
    private var cities: List<City>? = null
    private val trie = Trie()

    init {
        // parse list
        parseList()

        // build trie
        buildTrie()

    }

    private fun parseList() {
        cities = try {
            context.resources.openRawResource(R.raw.cities).bufferedReader().readText()
                ?.let { adapter.fromJson(it) }.sortAlphabetically()
        } catch (e: JsonDataException) {
            null
        }
    }

    private fun buildTrie() {
        cities?.forEach {
            trie.insert("${it.name} ${it.country}", it)
        }
    }

    override suspend fun getList(): List<City>? {
        return withContext(dispatchers.io()) {
            return@withContext cities
        }
    }

    override suspend fun getTrie(): Trie {
        return withContext(dispatchers.io()) {
            return@withContext trie
        }
    }
}