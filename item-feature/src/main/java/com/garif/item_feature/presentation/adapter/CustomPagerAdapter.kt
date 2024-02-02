package com.garif.item_feature.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.imageview.ShapeableImageView

class CustomPagerAdapter(
    private val mContext: Context,
    private val images: List<Int>,
) :
    PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(mContext)
        val layout =
            inflater.inflate(com.garif.core.R.layout.item_photo, container, false) as ViewGroup
        val imageView = layout.findViewById<ShapeableImageView>(com.garif.core.R.id.iv_photo)
        imageView.setImageDrawable(ContextCompat.getDrawable(mContext, images[position]))
        container.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int = images.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
}