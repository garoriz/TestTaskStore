package com.garif.cataog_feature.presentation.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.garif.cataog_feature.domain.entity.Item

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