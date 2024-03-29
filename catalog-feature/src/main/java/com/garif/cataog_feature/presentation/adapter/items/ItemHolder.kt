package com.garif.cataog_feature.presentation.adapter.items

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.garif.cataog_feature.domain.entity.Item
import com.garif.cataog_feature.presentation.CatalogFragment
import com.garif.cataog_feature.presentation.adapter.CustomPagerAdapter
import com.garif.core.PhotoMapper
import com.garif.core.databinding.ItemBinding


class ItemHolder(
    private val binding: ItemBinding,
    private val navigateToItemFragment: (String) -> Unit,
    private val saveLikedItem: (String) -> Unit,
    private val fragment: CatalogFragment,
    private val likedItems: MutableList<com.garif.database.model.Item>,
) : RecyclerView.ViewHolder(binding.root) {
    private var item: Item? = null

    init {
        itemView.setOnClickListener {
            item?.id?.also(navigateToItemFragment)
        }
    }

    fun bind(item: Item) {
        this.item = item
        with(binding) {
            val viewPager = viewpagerPhotos
            viewPager.adapter =
                PhotoMapper().photoMapping[item.id]?.let {
                    CustomPagerAdapter(
                        fragment.requireContext(),
                        it,
                        item.id,
                        navigateToItemFragment
                    )
                }

            tlPhotos.setupWithViewPager(viewPager, true)

            for (likedItem in likedItems) {
                if (likedItem.id == item.id)
                    setImageLike()
            }
            ivLike.setOnClickListener {
                setImageLike()
                likedItems.add(com.garif.database.model.Item(item.id))
                item.id.also(saveLikedItem)
            }


            val priceString = "${item.price} ${item.priceUnit}"
            tvPrice.text = priceString
            tvPrice.paintFlags = tvPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            val priceWithDiscountString = "${item.priceWithDiscount} ${item.priceUnit}"
            tvPriceWithDiscount.text = priceWithDiscountString
            tvDiscount.text = item.discount
            tvTitle.text = item.title
            tvSubtitle.text = item.subtitle

            if (item.rating != null) {
                ivStar.isVisible = true
                tvRating.isVisible = true
                tvFeedbackCount.isVisible = true
                tvRating.text = item.rating.toString()
                tvFeedbackCount.text = item.feedbackCount
            }
        }
    }

    private fun setImageLike() {
        with(binding) {
            ivLike.setImageDrawable(
                ContextCompat.getDrawable(
                    fragment.requireContext(),
                    com.garif.core.R.drawable.like
                )
            )
        }
    }


    companion object {

        fun create(
            parent: ViewGroup,
            navigateToItemFragment: (String) -> Unit,
            saveLikedItem: (String) -> Unit,
            fragment: CatalogFragment,
            likedItems: MutableList<com.garif.database.model.Item>,
        ) = ItemHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), navigateToItemFragment, saveLikedItem, fragment, likedItems
        )
    }
}