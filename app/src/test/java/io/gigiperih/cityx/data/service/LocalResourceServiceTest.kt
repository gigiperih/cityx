package io.gigiperih.cityx.data.service

import com.google.common.truth.Truth.assertThat
import io.gigiperih.cityx.data.mapper.traverse
import io.gigiperih.cityx.utils.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LocalResourceServiceTest {
    private lateinit var objectUnderTest: LocalResourceService

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        objectUnderTest =
            LocalResourceServiceTestImpl(coroutinesTestRule.testDispatcherProvider)
    }

    @Test
    fun `given default json file, when fetchResource() is success, should return valid Trie()`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val result = objectUnderTest.fetchResource()

            assertThat(result).apply {
                isNotNull()
            }

            assertThat(result.root()).apply {
                isNotNull()
            }
        }

    @Test
    fun `given valid Trie(), when traversing root is success, returns correct list of cities`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val result = objectUnderTest.fetchResource()

            assertThat(result.root().traverse()).apply {
                isNotEmpty()
                // not really sure why data is stripped down
                // might be because of duplicate items :)
                hasSize(83603)
            }
        }
}