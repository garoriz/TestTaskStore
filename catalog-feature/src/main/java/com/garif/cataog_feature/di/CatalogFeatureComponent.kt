package com.garif.cataog_feature.di

import com.garif.cataog_feature.di.module.CatalogModule
import com.garif.cataog_feature.di.module.RepoModule
import com.garif.cataog_feature.di.module.ViewModelModule
import com.garif.cataog_feature.presentation.CatalogFragment
import com.garif.database.DbModule
import com.garif.network.NetworkModule
import dagger.Subcomponent

@Subcomponent(
    modules = [
        NetworkModule::class,
        ViewModelModule::class,
        CatalogModule::class,
        DbModule::class,
        RepoModule::class]
)
interface CatalogFeatureComponent {
    fun injectCatalogFragment(catalogFragment: CatalogFragment)
}