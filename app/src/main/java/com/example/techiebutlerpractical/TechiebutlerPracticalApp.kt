package com.example.techiebutlerpractical

import android.app.Application
import com.example.techiebutlerpractical.koin.ApplicationModule
import com.example.techiebutlerpractical.koin.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TechiebutlerPracticalApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TechiebutlerPracticalApp)
            modules(
                listOf(
                    ApplicationModule.appModule,
                    ViewModelModule.viewModule
                )
            )
        }
    }
}