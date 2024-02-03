package com.garif.personal_profile_feature.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.garif.core.di.ViewModelKey
import com.garif.core.util.AppViewModelFactory
import com.garif.personal_profile_feature.presentation.PersonalProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(
        factory: AppViewModelFactory,
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PersonalProfileViewModel::class)
    fun bindPersonalProfileViewModel(
        viewModel: PersonalProfileViewModel,
    ): ViewModel
}