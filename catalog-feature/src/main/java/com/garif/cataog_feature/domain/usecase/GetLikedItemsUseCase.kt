package com.garif.cataog_feature.domain.usecase

import com.garif.database.AppDatabase
import com.garif.database.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetLikedItemsUseCase @Inject constructor(
    private val db: AppDatabase
) {
    suspend operator fun invoke(): List<Item> {
        return withContext(Dispatchers.IO) {
            db.itemDao().get()
        }
    }
}