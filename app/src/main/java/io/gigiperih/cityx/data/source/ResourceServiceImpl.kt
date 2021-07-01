package io.gigiperih.cityx.data.source

class ResourceServiceImpl : ResourceService {
    /**
     * TODO: might need coroutines
     */
    override fun get(fileName: String) =
        this::class.java.classLoader?.getResource(fileName)?.readText()
}