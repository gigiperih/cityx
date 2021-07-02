package io.gigiperih.cityx.arch

import io.gigiperih.cityx.data.service.LocalResourceService
import io.gigiperih.cityx.data.service.LocalResourceServiceTestImpl

open class BaseCityTest {
    protected val testService: LocalResourceService = LocalResourceServiceTestImpl()

//    protected val singleDataSet = service.get(file = "city.json")
//    protected val smallDataSet = service.get(file = "cities_2.json")
//    protected val mediumDataSet = service.get(file = "cities_100.json")
//    protected val largeDataSet = service.get(file = "cities_20k.json")
//    protected val massiveDataSet = service.get(file = "cities_100k.json")
//    protected val massiveSortedDataSet = service.get(file = "cities_100k.json")
//    protected val incompleteDataSet = service.get(file = "incomplete.json")
//    protected val invalidDataSet = service.get(file = "invalid.json")

//    protected fun provideSingleDataSet() = testService.get("city.json")
//    protected fun provideSmallDataSet() = testService.get("cities_2.json")
}