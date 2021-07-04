package io.gigiperih.cityx.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import io.gigiperih.cityx.data.City
import io.gigiperih.cityx.domain.interactor.CityInteractor
import io.gigiperih.cityx.domain.mapper.ResultState
import io.gigiperih.cityx.utils.CoroutineTestRule
import io.gigiperih.cityx.utils.flowWithResult
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CityViewModelTest {
    private lateinit var objectUnderTest: CityViewModel

    var mockedInteractor = mockk<CityInteractor>()

    @Rule
    @JvmField
    var coroutinesTestRule = CoroutineTestRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        objectUnderTest = CityViewModel(
            mockedInteractor,
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
    fun `trivial test`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        // TODO: fix unit test ASAP!
        val testObserver = createTestObserver()
        objectUnderTest.resultState.observeForever(testObserver)

//        coEvery { mockedInteractor.search("", 1) } returns
//                flowWithResult()

        objectUnderTest.get()

        val slot = slot<ResultState<List<City>>>()
        verify { testObserver.onChanged(capture(slot)) }

        //coVerify { mockedInteractor.search("", 1) }
    }

    private fun createTestObserver(): Observer<ResultState<List<City>>> =
        spyk(Observer { })
}