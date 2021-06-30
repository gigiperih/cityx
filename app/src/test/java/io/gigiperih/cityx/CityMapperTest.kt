package io.gigiperih.cityx

import org.junit.Test

class CityMapperTest {
    @Test
    fun `given sorted list of city, when mapped toHashMap, should return correct hashMap`() {
        TODO("Not yet implemented")
    }

    // TODO refactor to MVVM
    private fun List<City>?.toHashMap(): HashMap<String, City>? {
        if (this == null) return null

        val hash = hashMapOf<String, City>()
        this.map {
            // maf city + country as hash key
            hash["${it.name} ${it.country}"] = it
        }
        return hash
    }
}