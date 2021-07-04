package io.gigiperih.cityx.data.service

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.mapper.sortAlphabetically
import io.gigiperih.cityx.data.structure.Trie

class LocalResourceServiceImpl : LocalResourceService {
    companion object {
        const val DEFAULT_FILE = "cities_100k.json"
    }

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
            this::class.java.classLoader?.getResource(DEFAULT_FILE)?.readText()
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

    override fun getList(): List<City>? {
        return cities
    }

    override fun getTrie(): Trie {
        return trie
    }
}