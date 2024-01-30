package com.garif.teststore

import android.content.Context
import com.garif.teststore.di.RegistrationFeatureComponent
import dagger.BindsInstance
import dagger.Component

@Component()
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun createRegistrationComponent(): RegistrationFeatureComponent


    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}