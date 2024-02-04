package com.garif.favorites_feature.presentation.adapter.items

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.garif.favorites_feature.domain.entity.Item
import com.garif.favorites_feature.presentation.FavoritesFragment
import com.garif.favorites_feature.presentation.diffutil.ItemDiffItemCallback

class ItemListAdapter(
    private val navigateToItemFragment: (String) -> Unit,
    private val deleteLikedItem: (String) -> Unit,
    private val fragment: FavoritesFragment,
) : ListAdapter<Item, ItemHolder>(ItemDiffItemCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ItemHolder =
        ItemHolder.create(parent, navigateToItemFragment, deleteLikedItem, fragment)

    override fun onBindViewHolder(holder: ItemHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: MutableList<Item>?) {
        super.submitList(if (list == null) null else ArrayList(list))
    }
}