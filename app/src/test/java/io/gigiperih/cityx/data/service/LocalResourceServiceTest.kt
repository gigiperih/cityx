package io.gigiperih.cityx.data.service

import com.google.common.truth.Truth.assertThat
import io.gigiperih.cityx.FakeData
import io.gigiperih.cityx.data.structure.Trie
import org.junit.Test

class LocalResourceServiceTest {
    private lateinit var objectUnderTest: LocalResourceService

    @Test
    fun `given valid small json file, when getList() is success, should return list of cities`() {
        objectUnderTest = LocalResourceServiceTestImpl("cities_2.json")
        val result = objectUnderTest.getList()

        assertThat(result).apply {
            hasSize(2)
            isEqualTo(FakeData.sortedSample)
        }

    }

    @Test
    fun `given valid medium json file, when getList() is success, should return list of cities`() {
        objectUnderTest = LocalResourceServiceTestImpl("cities_99.json")
        val result = objectUnderTest.getList()

        assertThat(result).apply {
            hasSize(99)
        }

    }

    @Test
    fun `given valid large json file, when getList() is success, should return list of cities`() {
        objectUnderTest = LocalResourceServiceTestImpl("cities_100k.json")
        val result = objectUnderTest.getList()

        assertThat(result).apply {
            hasSize(100000)
        }

    }

    @Test
    fun `given valid large json file, when getTrie() is invoked, should return trie of cities`() {
        objectUnderTest = LocalResourceServiceTestImpl("cities_100k.json")
        val result = objectUnderTest.getTrie()

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Trie::class.java)
        }
    }
}