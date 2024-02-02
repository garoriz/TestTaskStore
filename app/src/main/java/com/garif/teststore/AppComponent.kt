package com.garif.teststore

import android.content.Context
import com.garif.cataog_feature.di.CatalogFeatureComponent
import com.garif.item_feature.di.ItemFeatureComponent
import com.garif.main_feature.di.MainFeatureComponent
import com.garif.teststore.di.RegistrationFeatureComponent
import dagger.BindsInstance
import dagger.Component

@Component
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun createRegistrationComponent(): RegistrationFeatureComponent

    fun createMainComponent(): MainFeatureComponent

    fun createCatalogComponent(): CatalogFeatureComponent

    fun createItemComponent(): ItemFeatureComponent


    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}