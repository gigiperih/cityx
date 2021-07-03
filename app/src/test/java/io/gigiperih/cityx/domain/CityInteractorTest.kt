package io.gigiperih.cityx.domain

import com.google.common.truth.Truth.assertThat
import io.gigiperih.cityx.domain.interactor.CityInteractor
import io.gigiperih.cityx.domain.interactor.CityInteractorImpl
import io.gigiperih.cityx.domain.repository.CityRepository
import io.mockk.mockk
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
    fun `trivial`() {
        TODO("Not yet implemented")
    }
}