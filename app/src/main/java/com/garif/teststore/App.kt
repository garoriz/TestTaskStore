package com.garif.teststore

import android.app.Application
import com.garif.main_feature.di.MainFeatureComponent
import com.garif.main_feature.di.MainFeatureComponentProvider
import com.garif.teststore.di.RegistrationFeatureComponent
import com.garif.teststore.di.RegistrationFeatureComponentProvider

class App : Application(), RegistrationFeatureComponentProvider, MainFeatureComponentProvider {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    override fun getRegistrationFeatureComponent(): RegistrationFeatureComponent {
        return appComponent.createRegistrationComponent()
    }

    override fun getMainFeatureComponent(): MainFeatureComponent {
        return appComponent.createMainComponent()
    }

}