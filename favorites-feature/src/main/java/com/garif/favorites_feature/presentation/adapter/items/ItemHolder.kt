package com.garif.favorites_feature.presentation.adapter.items

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.garif.core.PhotoMapper
import com.garif.core.databinding.ItemBinding
import com.garif.favorites_feature.domain.entity.Item
import com.garif.favorites_feature.presentation.FavoritesFragment
import com.garif.favorites_feature.presentation.adapter.CustomPagerAdapter

class ItemHolder(
    private val binding: ItemBinding,
    private val navigateToItemFragment: (String) -> Unit,
    private val deleteLikedItem: (String) -> Unit,
    private val fragment: FavoritesFragment,
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

            setImageLike()
            ivLike.setOnClickListener {
                item.id.also(deleteLikedItem)
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
            deleteLikedItem: (String) -> Unit,
            fragment: FavoritesFragment,
        ) = ItemHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), navigateToItemFragment, deleteLikedItem, fragment
        )
    }
}