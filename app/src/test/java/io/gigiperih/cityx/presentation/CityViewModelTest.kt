package io.gigiperih.cityx.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import io.gigiperih.cityx.domain.interactor.CityInteractor
import io.gigiperih.cityx.domain.mapper.ResultState
import io.gigiperih.cityx.utils.CoroutineTestRule
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch
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
    var mockedInteractor = mockk<CityInteractor>(relaxed = true)

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
    fun `given request without param, verify interactor executing expected param`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            launch {
                assertThat(objectUnderTest.resultState.value is ResultState.OnLoading).isTrue()
                objectUnderTest.search("")

                coVerify { mockedInteractor.search("") }
            }
        }

    @Test
    fun `given request with param, verify interactor executing expected param`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            launch {
                assertThat(objectUnderTest.resultState.value is ResultState.OnLoading).isTrue()
                objectUnderTest.search("gil")

                coVerify { mockedInteractor.search("gil") }
                coVerify(exactly = 0) { mockedInteractor.search("") }
            }
        }
}