package com.garif.personal_profile_feature.domain

import com.garif.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteAllInDbUseCase @Inject constructor(
    private val db: AppDatabase,
) {
    suspend operator fun invoke() {
        return withContext(Dispatchers.IO) {
            db.itemDao().delete()
            db.userDao().delete()
        }
    }
}