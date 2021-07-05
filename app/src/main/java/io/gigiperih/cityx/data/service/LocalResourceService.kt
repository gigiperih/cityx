package io.gigiperih.cityx.data.service

import io.gigiperih.cityx.data.structure.Trie

interface LocalResourceService {
    suspend fun fetchResource(): Trie
}