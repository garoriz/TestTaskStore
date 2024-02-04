package com.garif.favorites_feature.domain.usecase

import com.garif.favorites_feature.domain.entity.Item
import com.garif.favorites_feature.domain.repo.ItemsRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetLikedItemsUseCase @Inject constructor(
    private val itemsRepository: ItemsRepo,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    suspend operator fun invoke(likedItemIds: List<com.garif.database.model.Item>): List<Item> {
        return withContext(dispatcher) {
            itemsRepository.getItems(likedItemIds)
        }
    }
}