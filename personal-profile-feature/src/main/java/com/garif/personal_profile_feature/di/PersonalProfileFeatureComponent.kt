package com.garif.personal_profile_feature.di

import com.garif.database.DbModule
import com.garif.personal_profile_feature.di.module.PersonalProfileModule
import com.garif.personal_profile_feature.di.module.ViewModelModule
import com.garif.personal_profile_feature.presentation.PersonalProfileFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [
        ViewModelModule::class,
        DbModule::class,
        PersonalProfileModule::class
    ]
)
interface PersonalProfileFeatureComponent {
    fun injectPersonalProfileFragment(personalProfileFragment: PersonalProfileFragment)
}