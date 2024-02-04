package com.garif.favorites_feature.di.module

import com.garif.favorites_feature.data.ItemsRepoImpl
import com.garif.favorites_feature.domain.repo.ItemsRepo
import dagger.Binds
import dagger.Module

@Module
interface RepoModule {
    @Binds
    fun itemsRepo(
        impl: ItemsRepoImpl
    ): ItemsRepo
}