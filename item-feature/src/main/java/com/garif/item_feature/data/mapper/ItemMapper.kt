package com.garif.item_feature.data.mapper

import com.garif.item_feature.domain.entity.Item

class ItemMapper {
    fun map(response: com.garif.network.response.items.Item) = Item(
        id = response.id,
        title = response.title,
        subtitle = response.subtitle,
        price = "${response.price.price} ${response.price.unit}",
        priceWithDiscount = "${response.price.priceWithDiscount} ${response.price.unit}",
        discount = "-${response.price.discount}%",
        rating = response?.feedback?.rating,
        feedbackCount = response.feedback.count,
        availability = response.available,
        description = response.description,
        infoList = response.info,
        ingredients = response.ingredients
    )
}