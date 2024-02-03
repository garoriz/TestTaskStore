package com.garif.item_feature.presentation

import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.garif.core.PhotoMapper
import com.garif.core.navigation.navigationData
import com.garif.core.util.AppViewModelFactory
import com.garif.database.model.Item
import com.garif.item_feature.R
import com.garif.item_feature.databinding.FragmentItemBinding
import com.garif.item_feature.di.ItemFeatureComponentProvider
import com.garif.item_feature.presentation.adapter.CustomPagerAdapter
import com.garif.item_feature.presentation.adapter.characteristics.CharacteristicListAdapter
import com.garif.network.response.items.Info
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class ItemFragment : Fragment(R.layout.fragment_item) {
    @Inject
    lateinit var factory: AppViewModelFactory
    private lateinit var binding: FragmentItemBinding
    private val viewModel: ItemViewModel by viewModels {
        factory
    }
    private var characteristicListAdapter: CharacteristicListAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as ItemFeatureComponentProvider)
            .getItemFeatureComponent()
            .injectItemFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentItemBinding.bind(view)

        with(binding) {

            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }

            ivLike.setOnClickListener { }
        }

        initObservers()
        viewModel.onGetLikedItem(navigationData as? String ?: return)
        viewModel.onGetItem(navigationData as? String ?: return)
    }

    private fun initObservers() {
        viewModel.likedItem.observe(viewLifecycleOwner) { it ->
            it.fold(onSuccess = {
                if (it != null)
                    setImageLike()
                else
                    with(binding) {
                        ivLike.setOnClickListener {
                            setImageLike()
                            (navigationData as? String)?.let { id -> Item(id) }
                                ?.let { item -> viewModel.onSaveItem(item) }
                        }
                    }
            }, onFailure = {
                Log.e("e", it.message.toString())
            })
        }

        viewModel.item.observe(viewLifecycleOwner) { it ->
            it.fold(onSuccess = { item ->
                if (item != null) {
                    with(binding) {
                        val viewPager = viewpagerPhotos
                        viewPager.adapter =
                            PhotoMapper().photoMapping[item.id]?.let {
                                CustomPagerAdapter(
                                    requireContext(),
                                    it,
                                )
                            }

                        tlPhotos.setupWithViewPager(viewPager, true)

                        tvTitle.text = item.title
                        tvSubtitle.text = item.subtitle
                        val availabilityString =
                            getString(R.string.available_to_order) + " " + item.availability + " " + resources.getQuantityString(
                                R.plurals.piece,
                                item.availability
                            )
                        tvAvailable.text = availabilityString

                        if (item.rating != null) {
                            ratingBar.isVisible = true
                            ratingBar.rating = item.rating.toFloat()
                            tvRating.isVisible = true
                            tvRating.text = item.rating.toString()
                            tvFeedbackCount.isVisible = true
                            val feedbackCountString = "${item.feedbackCount} ${
                                resources.getQuantityString(
                                    R.plurals.feedback,
                                    item.feedbackCount
                                )
                            }"
                            tvFeedbackCount.text = feedbackCountString
                        }
                        tvPriceWithDiscount.text = item.priceWithDiscount
                        tvPrice.text = item.price
                        tvPrice.paintFlags = tvPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                        tvDiscount.text = item.discount
                        tvTitleInBtn.text = item.title
                        tvDescripionValue.text = item.ingredients

                        characteristicListAdapter = CharacteristicListAdapter()

                        binding.characteristics.run {
                            adapter = characteristicListAdapter
                        }

                        characteristicListAdapter?.submitList(item.infoList as MutableList<Info>)

                        tvPriceWithDiscountInBtn.text = item.priceWithDiscount
                        tvPriceInBtn.text = item.price
                        tvPriceInBtn.paintFlags = tvPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                        tvHideOrMoreDescription.setOnClickListener {
                            if (tvHideOrMoreDescription.text == getString(R.string.hide)) {
                                cvTitle.isVisible = false
                                tvDescripionValue.isVisible = false
                                tvHideOrMoreDescription.text = getString(R.string.more)
                            } else {
                                cvTitle.isVisible = true
                                tvDescripionValue.isVisible = true
                                tvHideOrMoreDescription.text = getString(R.string.hide)
                            }
                        }

                        tvCompositionValue.text = item.ingredients
                        if (!tvCompositionValue.isEllipsized()) {
                            tvHideOrMoreComposition.isVisible = false
                        }
                        tvHideOrMoreComposition.setOnClickListener {
                            if (tvHideOrMoreComposition.text == getString(R.string.more)) {
                                tvCompositionValue.maxLines = Int.MAX_VALUE
                                tvHideOrMoreComposition.text = getString(R.string.hide)
                            } else {
                                tvCompositionValue.maxLines = 2
                                tvHideOrMoreComposition.text = getString(R.string.more)
                            }
                        }
                    }
                }
            }, onFailure = {
                Log.e("e", it.message.toString())
            })
        }

        viewModel.error.observe(viewLifecycleOwner) {
            when (it) {
                is Exception -> {
                    showMessage(com.garif.core.R.string.error)
                }
            }
        }
    }

    private fun setImageLike() {
        with(binding) {
            ivLike.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    com.garif.core.R.drawable.like
                )
            )
        }
    }

    private fun showMessage(stringId: Int) {
        Snackbar.make(
            binding.root,
            stringId,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun TextView.isEllipsized() = layout?.text?.toString() != text?.toString()
}