package io.gigiperih.cityx

import com.google.common.truth.Truth.assertThat
import io.gigiperih.cityx.data.City
import org.junit.Before
import org.junit.Test
import kotlin.system.measureTimeMillis

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
        // assuming already sorted alphabetically
        val timeExecSmall = measureTimeMillis {
            FakeData.expectedSample.toHashMap()
        }

        // delta by considering jvm warm up process
        val timeExecLarge = measureTimeMillis {
            FakeLargeData.expectedSample.toHashMap()
        }

        // linear time complexity
        // if list size is 2 and took ~1ms to complete
        // then list size 100 should be ~50ms

        // requirement: should be better than linear time complexity: O(n)
        assertThat(timeExecLarge).apply {
            isLessThan(timeExecSmall * 50)
        }
    }
}