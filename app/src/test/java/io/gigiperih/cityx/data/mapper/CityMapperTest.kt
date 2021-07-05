package io.gigiperih.cityx.data.mapper

import com.google.common.truth.Truth.assertThat
import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.Coordinate
import io.gigiperih.cityx.data.structure.Trie
import io.gigiperih.cityx.utils.TestUtils
import io.gigiperih.cityx.utils.TestUtils.buildTrie
import org.junit.Test

class CityMapperTest {

    @Test
    fun `given medium unsorted list of cities, when sorting is success, returns sorted list`() {
        val unsortedList = TestUtils.buildUnsortedListOfCities("cities_20k.json")
        val result = unsortedList.sortAlphabetically()

        assertThat(result).hasSize(20000)

        // assert first item
        assertThat(result?.get(0)).isEqualTo(
            City(
                country = "ES",
                name = "A Estrada",
                _id = 3119746,
                coord = Coordinate(lat = 42.683331, lon = -8.48333)
            )
        )

        // assert last item
        assertThat(result?.get(19999)).isEqualTo(
            City(
                country = "KR", name = "괴내", _id = 6802827,
                coord = Coordinate(lat = 37.507198, lon = 127.320099)
            )
        )
    }

    @Test
    fun `given list is null, when sorting is failing, returns null`() {
        val invalidList: List<City>? = null
        val result = invalidList.sortAlphabetically()

        assertThat(result).isNull()
    }

    @Test
    fun `given valid trie, when traversing is success, returns valid sorted list of cities`() {
        val validTrie = TestUtils.buildSortedListOfCities("cities_20k.json").buildTrie()

        val result = validTrie.filterPrefix("De").traverse()

        assertThat(result).hasSize(168)
        assertThat(result[0]).isEqualTo(
            City(
                country = "NL", name = "De Heeze", _id = 6621532,
                coord = Coordinate(lat = 52.200611, lon = 5.95365)
            )
        )
        assertThat(result[167]).isEqualTo(
            City(
                country = "RU", name = "Deyskoye", _id = 566267,
                coord = Coordinate(lat = 43.47546, lon = 44.161819)
            )
        )
    }

    @Test
    fun `given valid trie, when traversing is failing, returns empty list`() {
        val validTrie = TestUtils.buildSortedListOfCities("cities_20k.json").buildTrie()

        val result = validTrie.filterPrefix("Xoxo").traverse()

        assertThat(result).isEmpty()
    }

    @Test
    fun `given empty trie, when traversing is failing, returns empty list`() {
        val emptyTrie = Trie()

        // if trie is valid, this query will returns result
        val result = emptyTrie.filterPrefix("De").traverse()

        assertThat(result).isEmpty()
    }
}