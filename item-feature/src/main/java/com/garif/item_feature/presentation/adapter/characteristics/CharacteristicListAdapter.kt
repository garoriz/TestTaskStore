package com.garif.item_feature.presentation.adapter.characteristics

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.garif.item_feature.presentation.diffutil.CharacteristicDiffItemCallback
import com.garif.network.response.items.Info

class CharacteristicListAdapter() :
    ListAdapter<Info, CharacteristicHolder>(CharacteristicDiffItemCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CharacteristicHolder = CharacteristicHolder.create(parent)

    override fun onBindViewHolder(holder: CharacteristicHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: MutableList<Info>?) {
        super.submitList(if (list == null) null else ArrayList(list))
    }
}