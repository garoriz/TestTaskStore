package com.garif.item_feature.domain.usecase

import com.garif.item_feature.domain.entity.Item
import com.garif.item_feature.domain.repo.ItemRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetItemUseCase @Inject constructor(
    private val itemRepository: ItemRepo,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(id: String): Item? {
        return withContext(dispatcher) {
            itemRepository.getItem(id)
        }
    }
}