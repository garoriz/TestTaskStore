package com.garif.cataog_feature.domain.usecase

import com.garif.cataog_feature.domain.entity.Item
import com.garif.cataog_feature.domain.repo.ItemsRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val itemsRepository: ItemsRepo,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(): List<Item> {
        return withContext(dispatcher) {
            itemsRepository.getItems()
        }
    }
}