package com.garif.database

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class DbModule {
    @Provides
    fun provideDb(context: Context): AppDatabase = AppDatabase(context)
}