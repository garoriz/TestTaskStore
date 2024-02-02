package com.garif.item_feature.di

import com.garif.database.DbModule
import com.garif.item_feature.di.module.ItemModule
import com.garif.item_feature.di.module.RepoModule
import com.garif.item_feature.di.module.ViewModelModule
import com.garif.item_feature.presentation.ItemFragment
import com.garif.network.NetworkModule
import dagger.Subcomponent

@Subcomponent(
    modules = [
        ViewModelModule::class,
        ItemModule::class,
        DbModule::class,
        RepoModule::class,
        NetworkModule::class]
)
interface ItemFeatureComponent {
    fun injectItemFragment(itemFragment: ItemFragment)
}