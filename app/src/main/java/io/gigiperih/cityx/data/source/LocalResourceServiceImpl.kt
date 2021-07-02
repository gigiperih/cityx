package io.gigiperih.cityx.data.source

class LocalResourceServiceImpl : LocalResourceService {
    /**
     * TODO: might need coroutines
     */
    override fun get(fileName: String) =
        this::class.java.classLoader?.getResource(fileName)?.readText()
}