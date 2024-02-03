package com.garif.personal_profile_feature.domain

import com.garif.database.AppDatabase
import com.garif.database.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val db: AppDatabase
) {
    suspend operator fun invoke(): User? {
        return withContext(Dispatchers.IO) {
            db.userDao().get()
        }
    }
}