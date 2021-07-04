package io.gigiperih.cityx.data.mapper

import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.structure.Trie

fun List<City>?.sortAlphabetically(): List<City>? {
    if (this == null) return null

    // https://docs.oracle.com/javase/10/docs/api/java/util/Arrays.html#sort(byte%5B%5D)
    // `Collection.sort()` works by calling the underlying `Arrays.sort()` method,
    // while the sorting itself uses `Insertion Sort` for arrays shorter than 47,
    // and `Quicksort` for the rest.
    return this.sortedBy { it.name }
}

fun Trie.Node?.traverse(): List<City> {
    val list = mutableListOf<City>()
    if (this?.city != null) {
        list.add(this.city!!)
    }

    if (!this?.childNodes.isNullOrEmpty()) {
        this?.childNodes?.forEach {
            list += it.value.traverse()
        }
    }

    return list
}