package com.garif.item_feature.domain.repo

import com.garif.item_feature.domain.entity.Item

interface ItemRepo {
    suspend fun getItem(id: String): Item?
}