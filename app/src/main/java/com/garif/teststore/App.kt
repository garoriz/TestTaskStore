package com.garif.teststore

import android.app.Application
import com.garif.teststore.di.RegistrationFeatureComponent
import com.garif.teststore.di.RegistrationFeatureComponentProvider

class App : Application(), RegistrationFeatureComponentProvider {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    override fun getRegistrationFeatureComponent(): RegistrationFeatureComponent {
        return appComponent.createRegistrationComponent()
    }

}