package com.garif.cataog_feature.data

import com.garif.cataog_feature.data.mappers.ItemMapper
import com.garif.cataog_feature.domain.entity.Item
import com.garif.cataog_feature.domain.repo.ItemsRepo
import com.garif.network.Api
import javax.inject.Inject

class ItemsRepoImpl @Inject constructor(
    private val api: Api,
    private val itemsMapper: ItemMapper
) : ItemsRepo {
    override suspend fun getItems(): List<Item> {
        return itemsMapper.map(api.getItems())
    }
}