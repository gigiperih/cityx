package io.gigiperih.cityx.data.mapper

import com.google.common.truth.Truth.assertThat
import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.Coordinate
import io.gigiperih.cityx.utils.TestUtils
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
        assertThat(result?.get(1999)).isEqualTo(
            City(
                country = "CZ", name = "Boskovice", _id = 3078910,
                coord = Coordinate(lat = 49.487511, lon = 16.659969)
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
    fun `given null list of city, when mapped toHashMap, should return null`() {
        TODO("Not yet implemented")
    }
}