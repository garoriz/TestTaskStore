package com.garif.favorites_feature.domain.entity

data class Item(
    val id: String,
    val title: String,
    val subtitle: String,
    var price: Int,
    val priceWithDiscount: Int,
    val discount: String,
    val rating: Double?,
    val feedbackCount: String,
    val priceUnit: String,
)