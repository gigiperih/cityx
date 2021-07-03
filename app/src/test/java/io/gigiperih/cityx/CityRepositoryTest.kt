package io.gigiperih.cityx

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.JsonEncodingException
import io.gigiperih.cityx.arch.BaseCityTest
import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.Coordinate
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

        assertThat(result[""]).apply {
            isEqualTo(FakeData.sortedSample)
            hasSize(2)
        }
    }

    @Test
    fun `given valid medium json file, when parsing is succeed, should return sorted hashmap of cities`() {
        val result = objectUnderTest.get(file = "cities_100.json")

        assertThat(result[""]).apply {
            hasSize(100)
        }

        assertThat(result[""]!![0]).apply {
            isEqualTo(
                City(
                    country = "UA", name = "Alupka", _id = 713514,
                    coord = Coordinate(lat = 44.416668, lon = 34.049999)
                )
            )
        }

        assertThat(result[""]!![99]).apply {
            isEqualTo(
                City(
                    country = "IL", name = "‘Azriqam", _id = 295582,
                    coord = Coordinate(lat = 31.75, lon = 34.700001)
                )
            )
        }
    }

    @Test
    fun `given valid large json file, when parsing is succeed, should return sorted hashmap of cities`() {
        val result = objectUnderTest.get(file = "cities_100k.json")

        assertThat(result[""]).apply {
            hasSize(100000)
        }
    }

    @Test
    fun `given valid but incomplete json file, when parsing is failing, should return empty`() {
        val result = objectUnderTest.get(file = "incomplete.json")

        assertThat(result).apply {
            isEmpty()
        }

    }

    @Test(expected = JsonEncodingException::class)
    fun `given invalid json file, when parsing is failing, should throws JsonEncodingException`() {
        objectUnderTest.get(file = "invalid.json")
    }

    @Test
    fun `trivial test for trie`() {
        val trie = objectUnderTest.getTrie()
        trie.insert("gilang", 10)
        trie.insert("gigi", 1)

        trie.insert("abigail", 45)
        trie.insert("abku", 420)
        trie.insert("abkun", 21)
        trie.insert("ablahu", 66)
        trie.insert("abor", 2)

        assertThat(trie.startsWith("ab")).isTrue()
        //assertThat(trie.startsNode("abku")).isEqualTo("")
        assertThat(trie.traverse(trie.startsNode("abk"))).isEqualTo("")
    }
}