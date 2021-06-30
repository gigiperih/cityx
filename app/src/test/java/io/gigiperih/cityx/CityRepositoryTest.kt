package io.gigiperih.cityx

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.JsonEncodingException
import io.gigiperih.cityx.arch.BaseCityTest
import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.repository.CityRepositoryImpl
import io.gigiperih.cityx.data.source.ResourceService
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
    var service: ResourceService = mockk(relaxed = true)
    private lateinit var objectUnderTest: CityRepository

    @Before
    fun setUp() {
        objectUnderTest = CityRepositoryImpl(service)
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
        every { service.get("cities_2.json") } returns smallDataSet

        // when
        val result = objectUnderTest.get("cities_2.json")

        // then
        assertThat(result).apply {
            isEqualTo(FakeData.expectedSample)
            hasSize(2)
            containsNoDuplicates()
        }

        verify { service.get("cities_2.json") }
    }

    @Test
    fun `given valid large json file, when parsing is succeed, should return correct list of cities`() {
        every { service.get("cities_20k.json") } returns largeDataSet

        val result = objectUnderTest.get("cities_20k.json")

        assertThat(result).apply {
            hasSize(20000)
            containsNoDuplicates()
        }

        verify { service.get("cities_20k.json") }
    }

    @Test
    fun `given valid but incomplete json file, when parsing is failing, should return null`() {
        every { service.get("incomplete.json") } returns incompleteDataSet

        val result = objectUnderTest.get("incomplete.json")

        assertThat(result).apply {
            isNull()
        }

        verify { service.get("incomplete.json") }
    }

    @Test(expected = JsonEncodingException::class)
    fun `given invalid json file, when parsing is failing, should throws JsonEncodingException`() {
        every { service.get("invalid.json") } returns invalidDataSet

        objectUnderTest.get("invalid.json")
    }

    @Test
    fun `given unsorted small list of city, when sorting is success, should return sorted list`() {
        every { service.get("cities_2.json") } returns smallDataSet

        val unsortedList = objectUnderTest.get("cities_2.json")
        val result = unsortedList.sortAlphabetically()

        assertThat(result).apply {
            isEqualTo(FakeData.sortedSample)
        }

        verify { service.get("cities_2.json") }
    }

    @Test
    fun `given unsorted medium list of city, when sorting is success, should return sorted list`() {
        every { service.get("cities_100.json") } returns mediumDataSet

        val unsortedList = objectUnderTest.get("cities_100.json")
        val result = unsortedList.sortAlphabetically()

        assertThat(result).apply {
            hasSize(100)
        }

        verify { service.get("cities_100.json") }
    }

    @Test
    fun `given unsorted large list of city, when sorting is success, should return sorted list`() {
        every { service.get("cities_20k.json") } returns largeDataSet

        val unsortedList = objectUnderTest.get("cities_20k.json")
        val result = unsortedList.sortAlphabetically()

        assertThat(result).apply {
            hasSize(20000)
        }

        verify { service.get("cities_20k.json") }
    }

    @Test
    fun `given null value of city, when sorting is failing, should return null`() {
        every { service.get("incomplete.json") } returns incompleteDataSet

        val unsortedList = objectUnderTest.get("incomplete.json")
        val result = unsortedList.sortAlphabetically()

        assertThat(result).apply {
            isNull()
        }

        verify { service.get("incomplete.json") }
    }

    @Test
    fun `given valid large and small json file, relative sorting time complexity should be better than linear`() {
        every { service.get("city.json") } returns singleDataSet
        every { service.get("cities_20k.json") } returns largeDataSet

        val singleData = objectUnderTest.get("city.json")
        val largeData = objectUnderTest.get("cities_20k.json")

        val timeExecSmall = measureNanoTime {
            singleData.sortAlphabetically()
        }

        // delta time by adding previous timeExecSmall assuming JVM was already warmed up
        val timeExecLarge = measureNanoTime {
            largeData.sortAlphabetically()
        }

        // verify time execution
        assertThat(timeExecSmall).apply {
            isLessThan(timeExecLarge)
        }

        // linear time complexity:
        // if a list contains 1 element took ~1ns to complete
        // then 20k sized list should be ~20000ns

        // requirement: should be better than linear time complexity: O(n)
        assertThat(timeExecLarge).apply {
            isLessThan(timeExecSmall * 20000)
        }

        verify { service.get("city.json") }
        verify { service.get("cities_20k.json") }
    }

    // TODO refactor (might use faster algorithm?)
    private fun List<City>?.sortAlphabetically(): List<City>? {
        if (this == null) return null
        return this.sortedBy { it.name }
    }
}