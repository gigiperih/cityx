package io.gigiperih.cityx

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonEncodingException
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
import kotlin.system.measureTimeMillis

class CityRepositoryTest {
    private val singleDataSet = getResource(fileName = "city.json")
    private val smallDataSet = getResource(fileName = "cities_2.json")
    private val mediumDataSet = getResource(fileName = "cities_100.json")
    private val largeDataSet = getResource(fileName = "cities_20k.json")
    private val incompleteDataSet = getResource(fileName = "incomplete.json")
    private val invalidDataSet = getResource(fileName = "invalid.json")

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
        val result = loadData(largeDataSet)

        assertThat(result).apply {
            hasSize(20000)
            containsNoDuplicates()
        }
    }

    @Test
    fun `given valid but incomplete json file, when parsing is failing, should return null`() {
        val result = loadData(incompleteDataSet)

        assertThat(result).apply {
            isNull()
        }
    }

    @Test(expected = JsonEncodingException::class)
    fun `given invalid json file, when parsing is failing, should throws JsonEncodingException`() {
        loadData(invalidDataSet)
    }

    @Test
    fun `given unsorted small list of city, when sorting is success, should return sorted list`() {
        val unsortedList = loadData(smallDataSet)
        val result = unsortedList.sortAlphabetically()

        assertThat(result).apply {
            isEqualTo(FakeData.sortedSample)
        }
    }

    @Test
    fun `given unsorted medium list of city, when sorting is success, should return sorted list`() {
        val unsortedList = loadData(mediumDataSet)
        val result = unsortedList.sortAlphabetically()

        assertThat(result).apply {
            hasSize(100)
        }
    }

    @Test
    fun `given unsorted large list of city, when sorting is success, should return sorted list`() {
        val unsortedList = loadData(largeDataSet)
        val result = unsortedList.sortAlphabetically()

        assertThat(result).apply {
            hasSize(20000)
        }
    }

    @Test
    fun `given null value of city, when sorting is failing, should return null`() {
        val unsortedList = loadData(incompleteDataSet)
        val result = unsortedList.sortAlphabetically()

        assertThat(result).apply {
            isNull()
        }
    }

    @Test
    fun `given valid large and small json file, relative sorting time complexity should be better than linear`() {

        val singleData = loadData(singleDataSet)
        val largeData = loadData(largeDataSet)

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

        println("large $timeExecLarge")
        println("small $timeExecSmall")
        // requirement: should be better than linear time complexity: O(n)
        assertThat(timeExecLarge).apply {
            isLessThan(timeExecSmall * 20000)
        }
    }

    // TODO refactor
    private fun loadData(json: String?): List<City>? {
        return try {
            val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            val listType = Types.newParameterizedType(List::class.java, City::class.java)

            val adapter = moshi.adapter<List<City>>(listType)

            json?.let { adapter.fromJson(it) }
        } catch (e: JsonDataException) {
            null
        }
    }

    // TODO refactor (might use faster algorithm?)
    private fun List<City>?.sortAlphabetically(): List<City>? {
        if (this == null) return null
        return this.sortedBy { it.name }
    }

    private fun getResource(fileName: String) =
        this::class.java.classLoader?.getResource(fileName)?.readText()
}