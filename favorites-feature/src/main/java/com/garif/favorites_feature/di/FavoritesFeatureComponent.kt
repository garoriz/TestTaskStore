package com.garif.favorites_feature.di

import com.garif.database.DbModule
import com.garif.favorites_feature.di.module.FavoritesModule
import com.garif.favorites_feature.di.module.RepoModule
import com.garif.favorites_feature.di.module.ViewModelModule
import com.garif.favorites_feature.presentation.FavoritesFragment
import com.garif.network.NetworkModule
import dagger.Subcomponent

@Subcomponent(
    modules = [
        NetworkModule::class,
        ViewModelModule::class,
        FavoritesModule::class,
        RepoModule::class,
        DbModule::class]
)
interface FavoritesFeatureComponent {
    fun injectFavoritesFragment(favoritesFragment: FavoritesFragment)
}