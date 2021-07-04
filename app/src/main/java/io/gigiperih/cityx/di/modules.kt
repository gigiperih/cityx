package io.gigiperih.cityx.di

import io.gigiperih.cityx.data.repository.CityRepositoryImpl
import io.gigiperih.cityx.data.service.LocalResourceService
import io.gigiperih.cityx.data.service.LocalResourceServiceImpl
import io.gigiperih.cityx.domain.interactor.CityInteractor
import io.gigiperih.cityx.domain.interactor.CityInteractorImpl
import io.gigiperih.cityx.domain.repository.CityRepository
import io.gigiperih.cityx.presentation.CityViewModel
import io.gigiperih.cityx.utils.dispatcher.DefaultDispatcherProvider
import io.gigiperih.cityx.utils.dispatcher.DispatcherProvider
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val cityModule = module {
    viewModel {
        CityViewModel(get())
    }

    single<DispatcherProvider> {
        DefaultDispatcherProvider()
    }

    single<CityInteractor> {
        CityInteractorImpl(get(), get())
    }

    single<CityRepository> {
        CityRepositoryImpl(get(), get())
    }

    single<LocalResourceService> {
        LocalResourceServiceImpl(androidContext(), get())
    }
}

val appModules = listOf(cityModule)