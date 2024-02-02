package com.garif.item_feature.presentation.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.garif.network.response.items.Info

class CharacteristicDiffItemCallback : DiffUtil.ItemCallback<Info>() {
    override fun areItemsTheSame(
        oldItem: Info,
        newItem: Info
    ): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(
        oldItem: Info,
        newItem: Info
    ): Boolean = oldItem == newItem
}