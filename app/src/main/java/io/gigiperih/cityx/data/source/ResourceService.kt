package io.gigiperih.cityx.data.source

interface ResourceService {
    fun get(fileName: String): String?
}