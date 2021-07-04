package io.gigiperih.cityx.presentation

import com.google.common.truth.Truth.assertThat
import io.gigiperih.cityx.domain.interactor.CityInteractor
import io.gigiperih.cityx.utils.CoroutineTestRule
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CityViewModelTest {
    private lateinit var objectUnderTest: CityViewModel
    var mockedInteractor = mockk<CityInteractor>()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

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
    fun `trivial test`() {
        TODO("Not yet implemented")
    }
}