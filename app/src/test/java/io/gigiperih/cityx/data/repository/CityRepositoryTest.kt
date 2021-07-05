package io.gigiperih.cityx.data.repository

import com.google.common.truth.Truth.assertThat
import io.gigiperih.cityx.data.service.LocalResourceService
import io.gigiperih.cityx.domain.repository.CityRepository
import io.gigiperih.cityx.utils.CoroutineTestRule
import io.gigiperih.cityx.utils.TestUtils
import io.gigiperih.cityx.utils.TestUtils.buildTrie
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CityRepositoryTest {
    private lateinit var objectUnderTest: CityRepository
    var mockedService = mockk<LocalResourceService>()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        objectUnderTest =
            CityRepositoryImpl(mockedService, coroutinesTestRule.testDispatcherProvider)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `verify objectUnderTest is not null`() {
        assertThat(objectUnderTest).isNotNull()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `given valid request, when getCities() is success, returns expected trie`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery { mockedService.fetchResource() } returns
                    TestUtils.buildSortedListOfCities("cities_100.json").buildTrie()

            val result = objectUnderTest.getCities()

            assertThat(result).apply {
                isNotNull()
            }

            coVerify { mockedService.fetchResource() }

        }
}