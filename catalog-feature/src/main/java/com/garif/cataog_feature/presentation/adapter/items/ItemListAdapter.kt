package com.garif.cataog_feature.presentation.adapter.items

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.garif.cataog_feature.domain.entity.Item
import com.garif.cataog_feature.presentation.CatalogFragment
import com.garif.cataog_feature.presentation.diffutils.ItemDiffItemCallback

class ItemListAdapter(
    private val navigateToItemFragment: (String) -> Unit,
    private val saveLikedItem: (String) -> Unit,
    private val fragment: CatalogFragment,
    private val likedItems: List<com.garif.database.model.Item>,
) : ListAdapter<Item, ItemHolder>(ItemDiffItemCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ItemHolder =
        ItemHolder.create(parent, navigateToItemFragment, saveLikedItem, fragment, likedItems)

    override fun onBindViewHolder(holder: ItemHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: MutableList<Item>?) {
        super.submitList(if (list == null) null else ArrayList(list))
    }
}