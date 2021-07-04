package io.gigiperih.cityx.fake

import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.Coordinate
import io.gigiperih.cityx.data.structure.Trie

object FakeData {
    val singleSample = listOf(
        City(
            country = "RU",
            name = "Novinki",
            _id = 519188,
            coord = Coordinate(lon = 37.666668, lat = 55.683334)
        )
    )

    val sortedSample = listOf(
        City(
            country = "UA",
            name = "Hurzuf",
            _id = 707860,
            coord = Coordinate(lon = 34.283333, lat = 44.549999)
        ),
        City(
            country = "RU",
            name = "Novinki",
            _id = 519188,
            coord = Coordinate(lon = 37.666668, lat = 55.683334)
        )
    )

    val sortedTrie = Trie().apply {
        insert(
            "Novinki RU", City(
                country = "RU",
                name = "Novinki",
                _id = 519188,
                coord = Coordinate(lon = 37.666668, lat = 55.683334)
            )
        )

        insert(
            "Hurzuf UA", City(
                country = "UA",
                name = "Hurzuf",
                _id = 707860,
                coord = Coordinate(lon = 34.283333, lat = 44.549999)
            )
        )
    }

    val hashMapOfExpectedSample = hashMapOf(
        "Hurzuf UA" to City(
            country = "UA",
            name = "Hurzuf",
            _id = 707860,
            coord = Coordinate(lon = 34.283333, lat = 44.549999)
        ),
        "Novinki RU" to City(
            country = "RU",
            name = "Novinki",
            _id = 519188,
            coord = Coordinate(lon = 37.666668, lat = 55.683334)
        )
    )
}