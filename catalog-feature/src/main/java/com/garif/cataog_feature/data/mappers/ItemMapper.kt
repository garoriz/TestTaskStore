package com.garif.cataog_feature.data.mappers

import com.garif.cataog_feature.domain.entity.Item
import com.garif.network.response.items.ItemsResponse
import com.garif.network.response.items.Item as ItemResponse

class ItemMapper {
    fun map(response: ItemsResponse): MutableList<Item> {
        return response.items.map(this::map) as MutableList<Item>
    }

    private fun map(item: ItemResponse): Item = Item(
        id = item.id,
        title = item.title,
        subtitle = item.subtitle,
        price = item.price.price.toInt(),
        priceWithDiscount = item.price.priceWithDiscount.toInt(),
        discount = "-${item.price.discount}%",
        rating = item?.feedback?.rating,
        feedbackCount = "(${item.feedback.count})",
        priceUnit = item.price.unit,
        tags = item.tags
    )
}