package io.gigiperih.cityx

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.JsonEncodingException
import io.gigiperih.cityx.arch.BaseCityTest
import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.repository.CityRepositoryImpl
import io.gigiperih.cityx.data.source.LocalResourceService
import io.gigiperih.cityx.domain.repository.CityRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.system.measureNanoTime

class CityRepositoryTest : BaseCityTest() {
    var serviceLocal: LocalResourceService = mockk(relaxed = true)
    private lateinit var objectUnderTest: CityRepository

    @Before
    fun setUp() {
        objectUnderTest = CityRepositoryImpl(serviceLocal)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `verify objectUnderTest is not null`() {
        assertThat(objectUnderTest).isNotNull()
    }

    @Test
    fun `given valid json file, when parsing is succeed, should return correct list of cities`() {
        // given
        every { serviceLocal.get("cities_2.json") } returns smallDataSet

        // when
        val result = objectUnderTest.get("cities_2.json")

        // then
        assertThat(result).apply {
            isEqualTo(FakeData.expectedSample)
            hasSize(2)
            containsNoDuplicates()
        }

        verify { serviceLocal.get("cities_2.json") }
    }

    @Test
    fun `given valid large json file, when parsing is succeed, should return correct list of cities`() {
        every { serviceLocal.get("cities_20k.json") } returns largeDataSet

        val result = objectUnderTest.get("cities_20k.json")

        assertThat(result).apply {
            hasSize(20000)
            containsNoDuplicates()
        }

        verify { serviceLocal.get("cities_20k.json") }
    }

    @Test
    fun `given valid but incomplete json file, when parsing is failing, should return null`() {
        every { serviceLocal.get("incomplete.json") } returns incompleteDataSet

        val result = objectUnderTest.get("incomplete.json")

        assertThat(result).apply {
            isNull()
        }

        verify { serviceLocal.get("incomplete.json") }
    }

    @Test(expected = JsonEncodingException::class)
    fun `given invalid json file, when parsing is failing, should throws JsonEncodingException`() {
        every { serviceLocal.get("invalid.json") } returns invalidDataSet

        objectUnderTest.get("invalid.json")
    }

    @Test
    fun `given unsorted small list of city, when sorting is success, should return sorted list`() {
        every { serviceLocal.get("cities_2.json") } returns smallDataSet

        val unsortedList = objectUnderTest.get("cities_2.json")
        val result = unsortedList.sortAlphabetically()

        assertThat(result).apply {
            isEqualTo(FakeData.sortedSample)
        }

        verify { serviceLocal.get("cities_2.json") }
    }

    @Test
    fun `given unsorted medium list of city, when sorting is success, should return sorted list`() {
        every { serviceLocal.get("cities_100.json") } returns mediumDataSet

        val unsortedList = objectUnderTest.get("cities_100.json")
        val result = unsortedList.sortAlphabetically()

        assertThat(result).apply {
            hasSize(100)
        }

        verify { serviceLocal.get("cities_100.json") }
    }

    @Test
    fun `given unsorted large list of city, when sorting is success, should return sorted list`() {
        every { serviceLocal.get("cities_20k.json") } returns largeDataSet

        val unsortedList = objectUnderTest.get("cities_20k.json")
        val result = unsortedList.sortAlphabetically()

        assertThat(result).apply {
            hasSize(20000)
        }

        verify { serviceLocal.get("cities_20k.json") }
    }

    @Test
    fun `given null value of city, when sorting is failing, should return null`() {
        every { serviceLocal.get("incomplete.json") } returns incompleteDataSet

        val unsortedList = objectUnderTest.get("incomplete.json")
        val result = unsortedList.sortAlphabetically()

        assertThat(result).apply {
            isNull()
        }

        verify { serviceLocal.get("incomplete.json") }
    }

    @Test
    fun `given valid large json file, relative sorting time complexity should be better than linear`() {
        every { serviceLocal.get("city.json") } returns singleDataSet
        every { serviceLocal.get("cities_20k.json") } returns largeDataSet

        val singleData = objectUnderTest.get("city.json")
        val largeData = objectUnderTest.get("cities_20k.json")

        val singleDataExecTime = measureNanoTime {
            singleData.sortAlphabetically()
        }

        val largeDataExecTime = measureNanoTime {
            largeData.sortAlphabetically()
        }

        assertThat(singleData).hasSize(1)
        assertThat(largeData).hasSize(20000)

        // verify time execution
        assertThat(singleDataExecTime).apply {
            isLessThan(largeDataExecTime)
        }

        // linear time complexity:
        // if a list contains 1 element took ~1ns to complete
        // then 20k sized list should be ~20000ns

        // requirement: should be better than linear time complexity: O(n)
        assertThat(largeDataExecTime).apply {
            isLessThan(singleDataExecTime * 20000)
        }

        verify { serviceLocal.get("city.json") }
        verify { serviceLocal.get("cities_20k.json") }
    }

    @Test
    fun `given valid massive json file, relative sorting time complexity should be better than linear`() {
        every { serviceLocal.get("city.json") } returns singleDataSet
        every { serviceLocal.get("cities_110k.json") } returns massiveDataSet

        val singleData = objectUnderTest.get("city.json")
        val massiveData = objectUnderTest.get("cities_110k.json")

        val singleDataExecTime = measureNanoTime {
            singleData.sortAlphabetically()
        }

        val massiveDataExecTime = measureNanoTime {
            massiveData.sortAlphabetically()
        }

        assertThat(singleData).hasSize(1)
        assertThat(massiveData).hasSize(100000)

        // verify time execution
        assertThat(singleDataExecTime).apply {
            isLessThan(massiveDataExecTime)
        }

        // linear time complexity:
        // if a list contains 1 element took ~1ns to complete
        // then 110k sized list should be ~100000ns

        // requirement: should be better than linear time complexity: O(n)
        assertThat(massiveDataExecTime).apply {
            isLessThan(singleDataExecTime * 100000)
        }

        verify { serviceLocal.get("city.json") }
        verify { serviceLocal.get("cities_110k.json") }
    }

    private fun List<City>?.sortAlphabetically(): List<City>? {
        if (this == null) return null

        // https://docs.oracle.com/javase/10/docs/api/java/util/Arrays.html#sort(byte%5B%5D)
        // `Collection.sort()` works by calling the underlying `Arrays.sort()` method,
        // while the sorting itself uses `Insertion Sort` for arrays shorter than 47,
        // and `Quicksort` for the rest.
        return this.sortedBy { it.name }
    }
}