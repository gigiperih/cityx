package io.gigiperih.cityx.arch

import io.gigiperih.cityx.data.service.LocalResourceService
import io.gigiperih.cityx.data.service.LocalResourceServiceTestImpl

open class BaseCityTest {
    protected val testService: LocalResourceService = LocalResourceServiceTestImpl("")
}