package com.garif.teststore.di

import com.garif.database.DbModule
import com.garif.teststore.di.module.RegistrationModule
import com.garif.teststore.di.module.ViewModelModule
import com.garif.teststore.presentation.RegistrationFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [
        ViewModelModule::class,
        DbModule::class,
        RegistrationModule::class
    ]
)
interface RegistrationFeatureComponent {
    fun injectRegistrationFragment(registrationFragment: RegistrationFragment)
}