package io.gigiperih.cityx.utils

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.mapper.sortAlphabetically
import io.gigiperih.cityx.data.structure.Trie

/**
 * utils for preparing data to be tested
 * not necessary good approach, but kinda hard to provide mock for large data
 */
object TestUtils {
    /**
     * helper to build list from json file
     */
    fun buildSortedListOfCities(file: String): List<City>? {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val listType = Types.newParameterizedType(List::class.java, City::class.java)
        val adapter = moshi.adapter<List<City>>(listType)

        return try {
            this::class.java.classLoader?.getResource(file)
                ?.readText()
                ?.let { adapter.fromJson(it) }.sortAlphabetically()
        } catch (e: JsonDataException) {
            null
        }
    }

    fun buildUnsortedListOfCities(file: String): List<City>? {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val listType = Types.newParameterizedType(List::class.java, City::class.java)
        val adapter = moshi.adapter<List<City>>(listType)

        return try {
            this::class.java.classLoader?.getResource(file)
                ?.readText()
                ?.let { adapter.fromJson(it) }.sortAlphabetically()
        } catch (e: JsonDataException) {
            null
        }
    }

    /**
     * helper to build trie from list of cities
     */
    fun List<City>?.buildTrie(): Trie {
        val trie = Trie()
        this?.forEach {
            trie.insert("${it.name} ${it.country}", it)
        }

        return trie
    }
}