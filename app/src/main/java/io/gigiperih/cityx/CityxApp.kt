package io.gigiperih.cityx

import android.app.Application
import io.gigiperih.cityx.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class CityxApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        startKoin {
            androidContext(this@CityxApp)
            modules(appModules)
        }
    }
}