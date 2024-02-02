package com.garif.item_feature.domain.entity

import com.garif.network.response.items.Info

data class Item (
    val id: String,
    val title: String,
    val subtitle: String,
    var price: String,
    val priceWithDiscount: String,
    val discount: String,
    val rating: Double?,
    val feedbackCount: Int,
    val availability: Int,
    val description: String,
    val infoList: List<Info>,
    val ingredients: String,
)