package io.gigiperih.cityx

import com.google.common.truth.Truth.assertThat
import io.gigiperih.cityx.arch.BaseCityTest
import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.mapper.CityMapper.toHashMap
import org.junit.Test

class CityMapperTest : BaseCityTest() {

    @Test
    fun `given sorted small list of city, when mapped toHashMap, should return correct hashMap`() {
        val result = FakeData.expectedSample.toHashMap()

        assertThat(result).apply {
            hasSize(2)
            isEqualTo(FakeData.hashMapOfExpectedSample)
            containsKey("Hurzuf UA")
            containsKey("Novinki RU")
        }
    }

    @Test
    fun `given medium list of city, when mapped toHashMap, should return correct hashMap`() {
        val result = FakeLargeData.expectedSample.toHashMap()

        assertThat(result).apply {
            hasSize(100)
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
}