package io.gigiperih.cityx.data.source

interface LocalResourceService {
    fun get(fileName: String): String?
}