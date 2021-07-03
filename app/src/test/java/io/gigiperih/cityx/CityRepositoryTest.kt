package io.gigiperih.cityx

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.JsonEncodingException
import io.gigiperih.cityx.arch.BaseCityTest
import io.gigiperih.cityx.data.repository.CityRepositoryImpl
import io.gigiperih.cityx.domain.repository.CityRepository
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Test

class CityRepositoryTest : BaseCityTest() {
    private lateinit var objectUnderTest: CityRepository

    @Before
    fun setUp() {
        objectUnderTest = CityRepositoryImpl(testService)
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
    fun `given valid small json file, when parsing is succeed, should return sorted hashmap of cities`() {
        val result = objectUnderTest.get(file = "cities_2.json")

        assertThat(result).apply {
            isEqualTo(FakeData.sortedSample)
            hasSize(2)
        }
    }

    @Test
    fun `given valid medium json file, when parsing is succeed, should return sorted hashmap of cities`() {
        val result = objectUnderTest.get(file = "cities_100.json")

        // TODO add more checking
        assertThat(result).apply {
            hasSize(100)
        }
    }

    @Test
    fun `given valid large json file, when parsing is succeed, should return sorted hashmap of cities`() {
        val result = objectUnderTest.get(file = "cities_100k.json")

        assertThat(result).apply {
            hasSize(100000)
        }
    }

    @Test
    fun `given valid but incomplete json file, when parsing is failing, should return null`() {
        val result = objectUnderTest.get(file = "incomplete.json")

        assertThat(result).apply {
            isNull()
        }

    }

    @Test(expected = JsonEncodingException::class)
    fun `given invalid json file, when parsing is failing, should throws JsonEncodingException`() {
        objectUnderTest.get(file = "invalid.json")
    }

    @Test
    fun `trivial test for trie`() {
        val result = objectUnderTest.get(file = "cities_100k.json")
        val trie = objectUnderTest.getTrie(result)

        assertThat(trie.traverse(trie.filterPrefix("ba"))).hasSize(1764)
    }
}