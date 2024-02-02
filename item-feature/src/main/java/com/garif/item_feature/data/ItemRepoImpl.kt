package com.garif.item_feature.data

import com.garif.item_feature.data.mapper.ItemMapper
import com.garif.item_feature.domain.entity.Item
import com.garif.item_feature.domain.repo.ItemRepo
import com.garif.network.Api
import javax.inject.Inject

class ItemRepoImpl @Inject constructor(
    private val api: Api,
    private val itemMapper: ItemMapper
) : ItemRepo {
    override suspend fun getItem(id: String): Item? {
        val items = api.getItems().items
        for (item in items)
            if (item.id == id)
                return itemMapper.map(item)
        return null
    }
}