package com.garif.teststore

import android.app.Application
import com.garif.cataog_feature.di.CatalogFeatureComponent
import com.garif.cataog_feature.di.CatalogFeatureComponentProvider
import com.garif.item_feature.di.ItemFeatureComponent
import com.garif.item_feature.di.ItemFeatureComponentProvider
import com.garif.main_feature.di.MainFeatureComponent
import com.garif.main_feature.di.MainFeatureComponentProvider
import com.garif.teststore.di.RegistrationFeatureComponent
import com.garif.teststore.di.RegistrationFeatureComponentProvider

class App : Application(), RegistrationFeatureComponentProvider, MainFeatureComponentProvider,
    CatalogFeatureComponentProvider, ItemFeatureComponentProvider {
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

    override fun getCatalogFeatureComponent(): CatalogFeatureComponent {
        return appComponent.createCatalogComponent()
    }

    override fun getItemFeatureComponent(): ItemFeatureComponent {
        return appComponent.createItemComponent()
    }

}