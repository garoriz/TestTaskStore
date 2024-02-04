package com.garif.favorites_feature.domain.repo

import com.garif.database.model.Item

interface ItemsRepo {
    suspend fun getItems(likedItems: List<Item>): List<com.garif.favorites_feature.domain.entity.Item>
}