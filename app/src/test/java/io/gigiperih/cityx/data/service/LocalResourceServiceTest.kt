package io.gigiperih.cityx.data.service

import com.google.common.truth.Truth.assertThat
import io.gigiperih.cityx.data.structure.Trie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class LocalResourceServiceTest {
    private val objectUnderTest: LocalResourceService = LocalResourceServiceImpl()

    @Test
    fun `given default json file, when getList() is success, should return list of cities`() =
        runBlockingTest {
            val result = objectUnderTest.getList()

            assertThat(result).apply {
                hasSize(100000)
            }

        }

    @Test
    fun `given valid large json file, when getTrie() is invoked, should return trie of cities`() =
        runBlockingTest {
            val result = objectUnderTest.getTrie()

            assertThat(result).apply {
                isNotNull()
                isInstanceOf(Trie::class.java)
            }
        }
}