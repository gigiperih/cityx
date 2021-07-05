package io.gigiperih.cityx.data.repository

import com.google.common.truth.Truth.assertThat
import io.gigiperih.cityx.data.service.LocalResourceService
import io.gigiperih.cityx.domain.repository.CityRepository
import io.gigiperih.cityx.fake.FakeData
import io.gigiperih.cityx.utils.CoroutineTestRule
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
    fun `given valid request, when getList is success, should return expected list of cities`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
//            coEvery { mockedService.getList() } returns FakeData.sortedSample
//
//            val result = objectUnderTest.getList()
//
//            assertThat(result).apply {
//                isEqualTo(FakeData.sortedSample)
//                hasSize(2)
//            }
//
//            coVerify { mockedService.getList() }

            TODO("Not yet implemented")
        }

    @Test
    fun `given valid request, when getTrie is success, should return expected trie of cities`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
//            coEvery { mockedService.getTrie() } returns FakeData.sortedTrie
//
//            val result = objectUnderTest.getTrie()
//
//            assertThat(result).apply {
//                isNotNull()
//                isEqualTo(FakeData.sortedTrie)
//            }
//
//            coVerify { mockedService.getTrie() }

            TODO("Not yet implemented")
        }
}