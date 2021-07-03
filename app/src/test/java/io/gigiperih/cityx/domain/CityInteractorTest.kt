package io.gigiperih.cityx.domain

import com.google.common.truth.Truth.assertThat
import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.Coordinate
import io.gigiperih.cityx.domain.interactor.CityInteractor
import io.gigiperih.cityx.domain.interactor.CityInteractorImpl
import io.gigiperih.cityx.domain.repository.CityRepository
import io.gigiperih.cityx.fake.FakeData
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CityInteractorTest {
    var mockedRepo = mockk<CityRepository>()
    private lateinit var objectUnderTest: CityInteractor

    @Before
    fun setUp() {
        objectUnderTest = CityInteractorImpl(mockedRepo)
    }

    @Test
    fun `verify objectUnderTest is not null`() {
        assertThat(objectUnderTest).isNotNull()
    }

    @Test
    fun `given search param is empty, when search is success, returns correct list of cities`() {
        every { mockedRepo.getList() } returns FakeData.sortedSample

        val result = objectUnderTest.search(keywords = "", page = 1)

        assertThat(result).apply {
            hasSize(2)
            isEqualTo(FakeData.sortedSample)
        }

        verify { mockedRepo.getList() }
    }

    @Test
    fun `given search param is not empty, when result is found, returns correct list of cities`() {
        every { mockedRepo.getTrie() } returns FakeData.sortedTrie

        val result = objectUnderTest.search(keywords = "No", page = 1)

        assertThat(result).apply {
            hasSize(1)
            isEqualTo(
                listOf(
                    City(
                        country = "RU",
                        name = "Novinki",
                        _id = 519188,
                        coord = Coordinate(lat = 55.683334, lon = 37.666668)
                    )
                )
            )
        }

        verify { mockedRepo.getTrie() }
    }

    @Test
    fun `given search param is not empty, when result is not found, returns empty list`() {
        every { mockedRepo.getTrie() } returns FakeData.sortedTrie

        val result = objectUnderTest.search(keywords = "69", page = 1)

        assertThat(result).apply {
            isEmpty()
        }

        verify { mockedRepo.getTrie() }
    }

    @Test
    fun `given multi page of results, when page selected, returns correct chunked list of cities`() {
        TODO("Not yet implemented")
    }

    @Test
    fun `given large data, when search without param is success, returns default list without process`() {
        TODO("Not yet implemented")
    }

    @Test
    fun `given large data, when search with param is success, return list of results`() {
        TODO("Not yet implemented")
    }

    @Test
    fun `given large data, when search with param is success, return empty list`() {
        TODO("Not yet implemented")
    }
}