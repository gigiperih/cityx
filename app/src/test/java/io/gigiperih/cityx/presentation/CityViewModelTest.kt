package io.gigiperih.cityx.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import io.gigiperih.cityx.domain.interactor.CityInteractor
import io.gigiperih.cityx.domain.mapper.ResultState
import io.gigiperih.cityx.utils.CoroutineTestRule
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
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
    private var mockedInteractor = mockk<CityInteractor>(relaxed = true)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)

        objectUnderTest = CityViewModel(
            mockedInteractor
        )
    }

    @After
    fun tearDown() {
        unmockkAll()

        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `verify objectUnderTest is not null`() {
        assertThat(objectUnderTest).isNotNull()
    }

    @Test
    fun `trivial test`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val result = objectUnderTest.resultState.first()

        assertThat(result is ResultState.OnLoading).isTrue()
    }
}