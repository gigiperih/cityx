package io.gigiperih.cityx

import org.junit.Test

class CityServiceTest {
    @Test
    fun `given valid large json file, relative sorting time complexity should be better than linear`() {
//        every { service.get("city.json") } returns singleDataSet
//        every { service.get("cities_20k.json") } returns largeDataSet
//
//        val singleData = objectUnderTest.get("city.json")
//        val largeData = objectUnderTest.get("cities_20k.json")
//
//        val singleDataExecTime = measureNanoTime {
//            singleData.sortAlphabetically()
//        }
//
//        val largeDataExecTime = measureNanoTime {
//            largeData.sortAlphabetically()
//        }
//
//        assertThat(singleData).hasSize(1)
//        assertThat(largeData).hasSize(20000)
//
//        // verify time execution
//        assertThat(singleDataExecTime).apply {
//            isLessThan(largeDataExecTime)
//        }
//
//        // linear time complexity:
//        // if a list contains 1 element took ~1ns to complete
//        // then 20k sized list should be ~20000ns
//
//        // requirement: should be better than linear time complexity: O(n)
//        assertThat(largeDataExecTime).apply {
//            isLessThan(singleDataExecTime * 20000)
//        }
//
//        verify { service.get("city.json") }
//        verify { service.get("cities_20k.json") }
        TODO("Not yet implemented")
    }

    @Test
    fun `given valid massive json file, relative sorting time complexity should be better than linear`() {
//        every { service.get("city.json") } returns singleDataSet
//        every { service.get("cities_110k.json") } returns massiveDataSet
//
//        val singleData = objectUnderTest.get("city.json")
//        val massiveData = objectUnderTest.get("cities_110k.json")
//
//        val singleDataExecTime = measureNanoTime {
//            singleData.sortAlphabetically()
//        }
//
//        val massiveDataExecTime = measureNanoTime {
//            massiveData.sortAlphabetically()
//        }
//
//        assertThat(singleData).hasSize(1)
//        assertThat(massiveData).hasSize(100000)
//
//        // verify time execution
//        assertThat(singleDataExecTime).apply {
//            isLessThan(massiveDataExecTime)
//        }
//
//        // linear time complexity:
//        // if a list contains 1 element took ~1ns to complete
//        // then 110k sized list should be ~100000ns
//
//        // requirement: should be better than linear time complexity: O(n)
//        assertThat(massiveDataExecTime).apply {
//            isLessThan(singleDataExecTime * 100000)
//        }
//
//        verify { service.get("city.json") }
//        verify { service.get("cities_110k.json") }

        TODO("Not yet implemented")
    }
}