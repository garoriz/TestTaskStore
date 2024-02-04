package com.garif.favorites_feature.data

import com.garif.database.model.Item
import com.garif.favorites_feature.data.mappers.ItemMapper
import com.garif.favorites_feature.domain.repo.ItemsRepo
import com.garif.favorites_feature.domain.entity.Item as EntityItem
import com.garif.network.Api
import javax.inject.Inject

class ItemsRepoImpl @Inject constructor(
    private val api: Api,
    private val itemsMapper: ItemMapper
) : ItemsRepo {
    override suspend fun getItems(likedItems: List<Item>): List<EntityItem> {
        val responseItems = api.getItems().items
        val resultItems = mutableListOf<EntityItem>()
        for (item in responseItems)
            if (likedItems.contains(Item(item.id))) {
                resultItems.add(itemsMapper.map(item))
            }
        return resultItems
    }
}