package io.gigiperih.cityx.domain

import com.google.common.truth.Truth.assertThat
import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.Coordinate
import io.gigiperih.cityx.domain.interactor.CityInteractor
import io.gigiperih.cityx.domain.interactor.CityInteractorImpl
import io.gigiperih.cityx.domain.repository.CityRepository
import io.gigiperih.cityx.fake.FakeData
import io.gigiperih.cityx.utils.TestUtils.buildSortedListOfCities
import io.gigiperih.cityx.utils.TestUtils.buildTrie
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import kotlin.system.measureNanoTime

@ExperimentalCoroutinesApi
class CityInteractorTest {
    private lateinit var objectUnderTest: CityInteractor
    var mockedRepo = mockk<CityRepository>()

    @Before
    fun setUp() {
        objectUnderTest = CityInteractorImpl(mockedRepo)
    }

    @Test
    fun `verify objectUnderTest is not null`() {
        assertThat(objectUnderTest).isNotNull()
    }

    @Test
    fun `given search param is empty, when search is success, returns correct list of cities`() =
        runBlockingTest {
            coEvery { mockedRepo.getList() } returns FakeData.sortedSample

            val result = objectUnderTest.search(keywords = "", page = 1)

            assertThat(result).apply {
                hasSize(2)
                isEqualTo(FakeData.sortedSample)
            }

            coVerify { mockedRepo.getList() }
        }

    @Test
    fun `given search param is not empty, when result is found, returns correct list of cities`() =
        runBlockingTest {
            coEvery { mockedRepo.getTrie() } returns FakeData.sortedTrie

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

            coVerify { mockedRepo.getTrie() }
        }

    @Test
    fun `given search param is not empty, when result is not found, returns empty list`() =
        runBlockingTest {
            coEvery { mockedRepo.getTrie() } returns FakeData.sortedTrie

            val result = objectUnderTest.search(keywords = "69", page = 1)

            assertThat(result).apply {
                isEmpty()
            }

            coVerify { mockedRepo.getTrie() }
        }

    @Test
    fun `given multi page of results, when page selected, returns correct chunked list of cities`() =
        runBlockingTest {
            coEvery { mockedRepo.getList() } returns buildSortedListOfCities("cities_99.json")

            val firstPage = objectUnderTest.search(keywords = "", page = 1)
            val lastPage = objectUnderTest.search(keywords = "", page = 10)

            assertThat(firstPage).apply {
                isNotEmpty()
                hasSize(10)
            }

            assertThat(lastPage).apply {
                isNotEmpty()
                hasSize(9)
            }

            coVerify { mockedRepo.getList() }
            coVerify(exactly = 0) { mockedRepo.getTrie() }
        }

    @Test
    fun `given large data, when search without param is success, returns default list without process`() =
        runBlockingTest {
            coEvery { mockedRepo.getList() } returns buildSortedListOfCities("cities_100k.json")

            val result = objectUnderTest.search(keywords = "", page = 1)

            assertThat(result).apply {
                isNotEmpty()
                hasSize(10)
            }

            coVerify { mockedRepo.getList() }
            coVerify(exactly = 0) { mockedRepo.getTrie() }
        }

    @Test
    fun `given large data, when search with param is success, return list of results`() =
        runBlockingTest {
            coEvery { mockedRepo.getTrie() } returns
                    buildSortedListOfCities("cities_100k.json").buildTrie()

            val result = objectUnderTest.search(keywords = "Ab", page = 1)

            assertThat(result).apply {
                isNotEmpty()
                hasSize(183)
            }
            assertThat(result?.get(0)).isEqualTo(
                City(
                    country = "AF", name = "Ab-e Kamari", _id = 1149550,
                    coord = Coordinate(lat = 35.087959, lon = 63.067799)
                )
            )

            coVerify { mockedRepo.getTrie() }
        }

    @Test
    fun `given large data, when search with param is failing, return empty list`() =
        runBlockingTest {
            coEvery { mockedRepo.getTrie() } returns
                    buildSortedListOfCities("cities_100k.json").buildTrie()

            val result = objectUnderTest.search(keywords = "xoxo", page = 1)

            assertThat(result).apply {
                isEmpty()
            }

            coVerify { mockedRepo.getTrie() }
        }

    @Test
    fun `given small and large data, verify searching time complexity is better than linear`() =
        runBlockingTest {
            val smallDataRepo = mockk<CityRepository>()
            val largeDataRepo = mockk<CityRepository>()

            val smallDataInteractor = CityInteractorImpl(smallDataRepo)
            val largeDataInteractor = CityInteractorImpl(largeDataRepo)

            coEvery { smallDataRepo.getTrie() } returns
                    buildSortedListOfCities("cities_2.json").buildTrie()
            coEvery { largeDataRepo.getTrie() } returns
                    buildSortedListOfCities("cities_100k.json").buildTrie()

            val smallTimeExec = measureNanoTime {
                smallDataInteractor.search(keywords = "No", page = 1)
            }

            val largeTimeExec = measureNanoTime {
                largeDataInteractor.search(keywords = "No", page = 1)
            }

            // relative time complexity
            // by checking execution time
            // should be better than linear
            assertThat(largeTimeExec).apply {
                isGreaterThan(smallTimeExec)

                // for linear time complexity
                // if 2 data = ~2ns
                // then 100k data should be around ~50000 ns
                isLessThan(smallTimeExec * 50000)
            }

            assertThat(smallDataInteractor.search(keywords = "No", page = 1))
                .hasSize(1)
            assertThat(largeDataInteractor.search(keywords = "No", page = 1))
                .hasSize(889)
        }
}