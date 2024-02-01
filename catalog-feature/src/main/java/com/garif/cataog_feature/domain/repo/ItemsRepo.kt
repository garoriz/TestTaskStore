package com.garif.cataog_feature.domain.repo

import com.garif.cataog_feature.domain.entity.Item

interface ItemsRepo {
    suspend fun getItems(): List<Item>
}