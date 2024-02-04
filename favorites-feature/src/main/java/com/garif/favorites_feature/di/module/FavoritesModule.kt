package com.garif.favorites_feature.di.module

import com.garif.favorites_feature.data.mappers.ItemMapper
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class FavoritesModule {
    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    fun provideItemMapper(): ItemMapper = ItemMapper()
}