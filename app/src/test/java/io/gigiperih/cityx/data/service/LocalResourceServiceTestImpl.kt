package io.gigiperih.cityx.data.service

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.structure.Trie
import io.gigiperih.cityx.utils.dispatcher.DefaultDispatcherProvider
import io.gigiperih.cityx.utils.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext

/**
 * imitate real parser so we don't have to mock context ;)
 */
class LocalResourceServiceTestImpl(
    private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) : LocalResourceService {
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val listType = Types.newParameterizedType(List::class.java, City::class.java)
    private val adapter = moshi.adapter<List<City>>(listType)
    private val trie = Trie()

    init {
        // build trie
        buildTrie()
    }

    private fun buildTrie() {
        val cities = try {
            this::class.java.classLoader?.getResource("cities_100k.json")?.readText()
                ?.let { adapter.fromJson(it) }
        } catch (e: JsonDataException) {
            null
        }
        cities?.forEach {
            trie.insert("${it.name} ${it.country}", it)
        }
    }

    override suspend fun fetchResource(): Trie {
        return withContext(dispatchers.io()) {
            return@withContext trie
        }
    }
}