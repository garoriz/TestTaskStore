package com.garif.teststore.domain.usecase

import com.garif.database.AppDatabase
import com.garif.database.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val db: AppDatabase
) {
    suspend operator fun invoke(user: User) {
        return withContext(Dispatchers.IO) {
            db.userDao().save(user)
        }
    }
}