package com.garif.cataog_feature.di.module

import com.garif.cataog_feature.data.ItemsRepoImpl
import com.garif.cataog_feature.domain.repo.ItemsRepo
import dagger.Binds
import dagger.Module

@Module
interface RepoModule {
    @Binds
    fun itemsRepo(
        impl: ItemsRepoImpl
    ): ItemsRepo
}