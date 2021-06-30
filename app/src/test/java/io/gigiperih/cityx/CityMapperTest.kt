package io.gigiperih.cityx

import com.google.common.truth.Truth.assertThat
import io.gigiperih.cityx.data.City
import org.junit.Test

class CityMapperTest {
    @Test
    fun `given sorted list of city, when mapped toHashMap, should return correct hashMap`() {
        val result = FakeData.expectedSample.toHashMap()

        assertThat(result).apply {
            hasSize(2)
            isEqualTo(FakeData.hashMapOfExpectedSample)
            containsKey("Hurzuf UA")
            containsKey("Novinki RU")
        }
    }

    @Test
    fun `given null list of city, when mapped toHashMap, should return null`() {
        val result: List<City>? = null
        result.toHashMap()

        assertThat(result).apply {
            isNull()
        }
    }

    @Test
    fun `given valid large and small list, relative mapping time complexity should be better than linear`() {
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