package com.garif.item_feature.di.module

import com.garif.item_feature.data.mapper.ItemMapper
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class ItemModule {
    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    fun provideItemMapper(): ItemMapper = ItemMapper()
}