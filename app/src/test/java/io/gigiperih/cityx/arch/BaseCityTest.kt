package io.gigiperih.cityx.arch

open class BaseCityTest {
    protected val singleDataSet = getResource(fileName = "city.json")
    protected val smallDataSet = getResource(fileName = "cities_2.json")
    protected val mediumDataSet = getResource(fileName = "cities_100.json")
    protected val largeDataSet = getResource(fileName = "cities_20k.json")
    protected val massiveDataSet = getResource(fileName = "cities_100k.json")
    protected val incompleteDataSet = getResource(fileName = "incomplete.json")
    protected val invalidDataSet = getResource(fileName = "invalid.json")

    private fun getResource(fileName: String) =
        this::class.java.classLoader?.getResource(fileName)?.readText()
}