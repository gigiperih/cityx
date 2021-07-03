package io.gigiperih.cityx.data.service

import com.google.common.truth.Truth.assertThat
import io.gigiperih.cityx.FakeData
import org.junit.Test

class LocalResourceServiceTest {
    private lateinit var objectUnderTest: LocalResourceService

    @Test
    fun `given valid small json file, when parsing is success, should return correct list of cities`() {
        objectUnderTest = LocalResourceServiceTestImpl("cities_2.json")
        val result = objectUnderTest.get(page = 1)

        assertThat(result).apply {
            hasSize(2)
            isEqualTo(FakeData.sortedSample)
        }

    }

    @Test
    fun `given valid json file, when parsing is success, should return chunked list of cities`() {
        objectUnderTest = LocalResourceServiceTestImpl("cities_99.json")
        val firstPage = objectUnderTest.get(page = 1)
        val lastPage = objectUnderTest.get(page = 10)

        assertThat(firstPage).apply {
            hasSize(10)
        }

        assertThat(lastPage).apply {
            hasSize(9)
        }

    }

    @Test
    fun `given valid large json file, when parsing is success, should return chunked list of cities`() {
        objectUnderTest = LocalResourceServiceTestImpl("cities_100k.json")
        val firstPage = objectUnderTest.get(page = 1)
        val lastPage = objectUnderTest.get(page = 10)

        assertThat(firstPage).apply {
            hasSize(10)
        }

        assertThat(lastPage).apply {
            hasSize(9)
        }

    }
}