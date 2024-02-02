package com.garif.item_feature.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.garif.core.di.ViewModelKey
import com.garif.core.util.AppViewModelFactory
import com.garif.item_feature.presentation.ItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(
        factory: AppViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ItemViewModel::class)
    fun bindItemViewModel(
        viewModel: ItemViewModel
    ): ViewModel
}