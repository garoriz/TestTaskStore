package com.garif.favorites_feature.data.mappers

import com.garif.favorites_feature.domain.entity.Item

class ItemMapper {

    fun map(item: com.garif.network.response.items.Item): Item = Item(
        id = item.id,
        title = item.title,
        subtitle = item.subtitle,
        price = item.price.price.toInt(),
        priceWithDiscount = item.price.priceWithDiscount.toInt(),
        discount = "-${item.price.discount}%",
        rating = item?.feedback?.rating,
        feedbackCount = "(${item.feedback.count})",
        priceUnit = item.price.unit,
    )
}