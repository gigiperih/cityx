package io.gigiperih.cityx

object FakeData {
    const val validSample = """
        [
            {"country":"UA","name":"Hurzuf","_id":707860,"coord":{"lon":34.283333,"lat":44.549999}},
            {"country":"RU","name":"Novinki","_id":519188,"coord":{"lon":37.666668,"lat":55.683334}}
        ]
    """

    const val unsortedSample = """
        [
            {"country":"RU","name":"Novinki","_id":519188,"coord":{"lon":37.666668,"lat":55.683334}},
            {"country":"UA","name":"Hurzuf","_id":707860,"coord":{"lon":34.283333,"lat":44.549999}}
        ]
    """

    const val incompleteSample = """
        [
            {"country":"IN","name":"State of Haryāna","_id":1270260}
        ]
    """

    const val invalidSample = """
        [
            {"country":"IN","name":"State of Haryāna","_id":1270260
        ]
    """

    val expectedSample = listOf(
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