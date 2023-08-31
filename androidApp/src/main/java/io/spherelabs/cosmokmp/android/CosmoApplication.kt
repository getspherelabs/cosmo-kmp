package io.spherelabs.cosmokmp.android

import android.app.Application
import io.spherelabs.cosmokmp.di.initKoin
import org.koin.android.ext.koin.androidContext

class CosmoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@CosmoApplication)
        }
    }
}
