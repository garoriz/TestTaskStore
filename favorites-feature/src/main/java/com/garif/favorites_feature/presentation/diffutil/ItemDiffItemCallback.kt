package com.garif.favorites_feature.presentation.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.garif.favorites_feature.domain.entity.Item


class ItemDiffItemCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(
        oldItem: Item,
        newItem: Item
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Item,
        newItem: Item
    ): Boolean = oldItem == newItem
}