package io.gigiperih.cityx.data

data class City(
    val country: String,
    val name: String,
    val _id: Long,
    val coord: Coordinate
)

data class Coordinate(
    val lat: Double,
    val lon: Double
)