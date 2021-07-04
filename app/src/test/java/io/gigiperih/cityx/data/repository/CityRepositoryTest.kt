package io.gigiperih.cityx.data.repository

import com.google.common.truth.Truth.assertThat
import io.gigiperih.cityx.data.service.LocalResourceService
import io.gigiperih.cityx.domain.repository.CityRepository
import io.gigiperih.cityx.fake.FakeData
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test

class CityRepositoryTest {
    var mockedService = mockk<LocalResourceService>()
    private lateinit var objectUnderTest: CityRepository

    @Before
    fun setUp() {
        objectUnderTest = CityRepositoryImpl(mockedService)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `verify objectUnderTest is not null`() {
        assertThat(objectUnderTest).isNotNull()
    }

    @Test
    fun `given valid request, when getList is success, should return expected list of cities`() {
        every { mockedService.getList() } returns FakeData.sortedSample

        val result = objectUnderTest.getList()

        assertThat(result).apply {
            isEqualTo(FakeData.sortedSample)
            hasSize(2)
        }

        verify { mockedService.getList() }
    }

    @Test
    fun `given valid request, when getTrie is success, should return expected trie of cities`() {
        every { mockedService.getTrie() } returns FakeData.sortedTrie

        val result = objectUnderTest.getTrie()

        assertThat(result).apply {
            isNotNull()
            isEqualTo(FakeData.sortedTrie)
        }

        verify { mockedService.getTrie() }
    }
}