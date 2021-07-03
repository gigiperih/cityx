package io.gigiperih.cityx.data.mapper

import io.gigiperih.cityx.data.City

class CityMapper {
    fun sortAlphabetically(unsortedList: List<City>?): List<City>? {
        if (unsortedList == null) return null

        // https://docs.oracle.com/javase/10/docs/api/java/util/Arrays.html#sort(byte%5B%5D)
        // `Collection.sort()` works by calling the underlying `Arrays.sort()` method,
        // while the sorting itself uses `Insertion Sort` for arrays shorter than 47,
        // and `Quicksort` for the rest.
        return unsortedList.sortedBy { it.name }
    }
}