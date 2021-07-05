package io.gigiperih.cityx.domain

import com.google.common.truth.Truth.assertThat
import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.data.Coordinate
import io.gigiperih.cityx.domain.interactor.CityInteractor
import io.gigiperih.cityx.domain.interactor.CityInteractorImpl
import io.gigiperih.cityx.domain.mapper.ResultState
import io.gigiperih.cityx.domain.repository.CityRepository
import io.gigiperih.cityx.fake.FakeData
import io.gigiperih.cityx.utils.CoroutineTestRule
import io.gigiperih.cityx.utils.TestUtils.buildSortedListOfCities
import io.gigiperih.cityx.utils.TestUtils.buildTrie
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.system.measureNanoTime

@ExperimentalCoroutinesApi
class CityInteractorTest {
    private lateinit var objectUnderTest: CityInteractor
    var mockedRepo = mockk<CityRepository>()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        objectUnderTest = CityInteractorImpl(
            mockedRepo,
            coroutinesTestRule.testDispatcherProvider
        )
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
    fun `given search param is empty, when search is success, returns correct flows`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery { mockedRepo.getCities() } returns FakeData.sortedSample.buildTrie()

            val flow = objectUnderTest.search(keywords = "")

            // flow count should be = 2
            // 1) loading 2) success
            assertThat(flow.count()).isEqualTo(2)
            assertThat(flow.first() is ResultState.OnLoading).isTrue()
            assertThat(flow.last() is ResultState.OnSuccess).isTrue()

            val result = flow.last() as ResultState.OnSuccess

            assertThat(result.data).apply {
                hasSize(2)
                isEqualTo(FakeData.sortedSample)
            }

            assertThat(result.message).apply {
                isNotEmpty()
                isEqualTo("Found 2 cities.")
            }

            coVerify { mockedRepo.getCities() }
        }

    @Test
    fun `given search param is not empty, when result is found, returns correct flows`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery { mockedRepo.getCities() } returns FakeData.sortedTrie

            val flow = objectUnderTest.search(keywords = "No")

            assertThat(flow.count()).isEqualTo(2)
            assertThat(flow.first() is ResultState.OnLoading).isTrue()
            assertThat(flow.last() is ResultState.OnSuccess).isTrue()

            val result = flow.last() as ResultState.OnSuccess

            assertThat(result.data).apply {
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

            coVerify { mockedRepo.getCities() }
        }

    @Test
    fun `given search param is not empty, when result is not found, returns Result not found`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery { mockedRepo.getCities() } returns FakeData.sortedTrie

            val flow = objectUnderTest.search(keywords = "Xoxo")
            assertThat(flow.count()).isEqualTo(2)
            assertThat(flow.first() is ResultState.OnLoading).isTrue()
            assertThat(flow.last() is ResultState.OnError).isTrue()

            val result = flow.last() as ResultState.OnError
            assertThat(result.message).isEqualTo("Result not found")

            coVerify { mockedRepo.getCities() }
        }


    @Test
    fun `given large data, when search without param is success, returns trie root`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery { mockedRepo.getCities() } returns
                    buildSortedListOfCities("cities_100k.json").buildTrie()

            val result =
                objectUnderTest.search(keywords = "").last() as ResultState.OnSuccess

            assertThat(result.data).apply {
                isNotEmpty()
                hasSize(83603)
            }

            coVerify { mockedRepo.getCities() }
        }

    @Test
    fun `given large data, when search with param is success, return list of results`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery { mockedRepo.getCities() } returns
                    buildSortedListOfCities("cities_100k.json").buildTrie()

            val result =
                objectUnderTest.search(keywords = "Ab").last() as ResultState.OnSuccess

            assertThat(result.data).apply {
                isNotEmpty()
                hasSize(183)
            }
            assertThat(result.data?.get(0)).isEqualTo(
                City(
                    country = "AF", name = "Ab-e Kamari", _id = 1149550,
                    coord = Coordinate(lat = 35.087959, lon = 63.067799)
                )
            )

            coVerify { mockedRepo.getCities() }
        }

    @Test
    fun `given large data, when search with param is failing, returns result not found`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery { mockedRepo.getCities() } returns
                    buildSortedListOfCities("cities_100k.json").buildTrie()

            val flow = objectUnderTest.search(keywords = "Xoxoxoxoxo")
            assertThat(flow.count()).isEqualTo(2)
            assertThat(flow.first() is ResultState.OnLoading).isTrue()
            assertThat(flow.last() is ResultState.OnError).isTrue()

            val result = flow.last() as ResultState.OnError
            assertThat(result.message).isEqualTo("Result not found")

            coVerify { mockedRepo.getCities() }
        }

    @Test
    fun `given small and large data, verify searching time complexity is better than linear`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val smallDataRepo = mockk<CityRepository>()
            val largeDataRepo = mockk<CityRepository>()

            val smallDataInteractor = CityInteractorImpl(smallDataRepo)
            val largeDataInteractor = CityInteractorImpl(largeDataRepo)

            coEvery { smallDataRepo.getCities() } returns
                    buildSortedListOfCities("cities_2.json").buildTrie()
            coEvery { largeDataRepo.getCities() } returns
                    buildSortedListOfCities("cities_100k.json").buildTrie()

            val smallTimeExec = measureNanoTime {
                smallDataInteractor.search(keywords = "No")
            }

            val largeTimeExec = measureNanoTime {
                largeDataInteractor.search(keywords = "No")
            }

            // relative time complexity
            // by checking execution time
            // should be better than linear
            assertThat(largeTimeExec).apply {
                // for linear time complexity
                // if 2 data = ~2ns
                // then 100k data should be around ~50000 ns
                isLessThan(smallTimeExec * 50000)
            }

            val smallResult = smallDataInteractor.search(keywords = "No")
                .last() as ResultState.OnSuccess

            // results > 10 items resulting in multi-page response
            val largeResult = largeDataInteractor.search(keywords = "No")
                .last() as ResultState.OnSuccess

            assertThat(smallResult.data)
                .hasSize(1)
            assertThat(largeResult.data)
                .hasSize(889)

        }
}