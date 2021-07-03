package io.gigiperih.cityx.data.mapper

import io.gigiperih.cityx.data.City

fun List<City>?.toHashMap(): HashMap<String, City>? {
    if (this == null) return null

    val hash = hashMapOf<String, City>()
    this.map {
        // map city + country as hash key
        hash[it.name] = it
    }
    return hash
}

fun List<City>?.sortAlphabetically(): List<City>? {
    if (this == null) return null

    // https://docs.oracle.com/javase/10/docs/api/java/util/Arrays.html#sort(byte%5B%5D)
    // `Collection.sort()` works by calling the underlying `Arrays.sort()` method,
    // while the sorting itself uses `Insertion Sort` for arrays shorter than 47,
    // and `Quicksort` for the rest.
    return this.sortedBy { it.name }
}