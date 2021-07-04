package io.gigiperih.cityx

import android.app.Application
import io.gigiperih.cityx.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CityxApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CityxApp)
            modules(appModules)
        }
    }
}