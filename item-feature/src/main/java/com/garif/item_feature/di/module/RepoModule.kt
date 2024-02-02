package com.garif.item_feature.di.module

import com.garif.item_feature.data.ItemRepoImpl
import com.garif.item_feature.domain.repo.ItemRepo
import dagger.Binds
import dagger.Module

@Module
interface RepoModule {
    @Binds
    fun itemRepo(
        impl: ItemRepoImpl,
    ): ItemRepo

}