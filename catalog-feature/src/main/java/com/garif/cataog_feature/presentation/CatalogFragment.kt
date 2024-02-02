package com.garif.cataog_feature.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.garif.cataog_feature.R
import com.garif.cataog_feature.databinding.FragmentCatalogBinding
import com.garif.cataog_feature.di.CatalogFeatureComponentProvider
import com.garif.cataog_feature.domain.entity.Item
import com.garif.cataog_feature.presentation.adapter.items.ItemListAdapter
import com.garif.core.navigate
import com.garif.core.util.AppViewModelFactory
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class CatalogFragment : Fragment(R.layout.fragment_catalog) {
    @Inject
    lateinit var factory: AppViewModelFactory
    private lateinit var binding: FragmentCatalogBinding
    private lateinit var popupMenu: PopupMenu
    private val viewModel: CatalogViewModel by viewModels {
        factory
    }
    private var itemsListAdapter: ItemListAdapter? = null
    private val allItemList: MutableList<Item> = mutableListOf()
    private val sortedAndFilteredItemList: MutableList<Item> = mutableListOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as CatalogFeatureComponentProvider)
            .getCatalogFeatureComponent()
            .injectCatalogFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCatalogBinding.bind(view)

        setPopup()

        with(binding) {
            cvSort.setOnClickListener {
                popupMenu.show()
            }

            setListenerToChip(chipAll)
            setListenerToChip(chipBody)
            setListenerToChip(chipFace)
            setListenerToChip(chipTan)
            setListenerToChip(chipMasks)
        }

        initObservers()
        viewModel.onGetLikedItem()
        viewModel.onGetItems()
    }

    private fun setPopup() {
        with(binding) {
            popupMenu = PopupMenu(requireContext(), cvSort)
            popupMenu.inflate(R.menu.sorting_popup_menu)

            popupMenu.setOnMenuItemClickListener { menuItem ->
                tvSortDescription.text = menuItem.title
                when (menuItem.itemId) {
                    R.id.by_popular -> {
                        sortedAndFilteredItemList.sortByDescending { it.rating }
                        itemsListAdapter?.submitList(sortedAndFilteredItemList)
                    }

                    R.id.by_decrease_price -> {
                        sortedAndFilteredItemList.sortByDescending { it.priceWithDiscount }
                        itemsListAdapter?.submitList(sortedAndFilteredItemList)
                    }

                    R.id.by_increase_price -> {
                        sortedAndFilteredItemList.sortBy { it.priceWithDiscount }
                        itemsListAdapter?.submitList(sortedAndFilteredItemList)
                    }
                }
                false
            }

            chipAll.setOnClickListener {
                submitAllItems()
            }
            setOnClickListenerOnFilterChip(chipBody, com.garif.core.R.string.body_tag)
            setOnClickListenerOnFilterChip(chipMasks, com.garif.core.R.string.mask_tag)
            setOnClickListenerOnFilterChip(chipFace, com.garif.core.R.string.face_tag)
            setOnClickListenerOnFilterChip(chipTan, com.garif.core.R.string.suntan_tag)
        }
    }

    private fun setOnClickListenerOnFilterChip(chip: Chip, stringTagId: Int) {
        chip.setOnClickListener {
            if (chip.isChecked) {
                sortedAndFilteredItemList.clear()
                sortedAndFilteredItemList.addAll(allItemList.filter {
                    it.tags.contains(
                        getString(
                            stringTagId
                        )
                    )
                })
                sortAndSubmitItems()
            } else {
                submitAllItems()
            }
        }
    }

    private fun submitAllItems() {
        sortedAndFilteredItemList.clear()
        sortedAndFilteredItemList.addAll(allItemList)
        sortAndSubmitItems()
    }

    private fun setListenerToChip(chip: Chip) {
        chip.setOnCheckedChangeListener { _, _ -> chipCloseBtnVisibility(chip) }
    }

    private fun chipCloseBtnVisibility(chip: Chip) {
        chip.isCloseIconVisible = chip.isChecked
    }

    private fun initObservers() {
        viewModel.likedItems.observe(viewLifecycleOwner) { it ->
            it.fold(onSuccess = {
                initItemsObservers(it)
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

    private fun initItemsObservers(likedItems: List<com.garif.database.model.Item>) {
        viewModel.items.observe(viewLifecycleOwner) { items ->
            items.fold(onSuccess = { responseItems ->
                itemsListAdapter = ItemListAdapter({
                    navigate(R.id.action_navigation_catalog_to_itemFragment, data = it)
                }, {
                    viewModel.onSaveItem(com.garif.database.model.Item(it))
                }, this@CatalogFragment, likedItems)

                binding.items.run {
                    adapter = itemsListAdapter
                }

                if (allItemList.isEmpty()) {
                    allItemList.addAll(responseItems)
                    sortedAndFilteredItemList.addAll(allItemList.sortedByDescending { it.rating })
                }
                itemsListAdapter?.submitList(sortedAndFilteredItemList)
            }, onFailure = {
                Log.e("e", it.message.toString())
            })
        }
    }

    private fun sortAndSubmitItems() {
        with(binding) {
            when (tvSortDescription.text) {
                getString(R.string.by_popular) -> {
                    sortedAndFilteredItemList.sortByDescending { it.rating }
                    itemsListAdapter?.submitList(sortedAndFilteredItemList)
                }

                getString(com.garif.core.R.string.by_decrease_price) -> {
                    sortedAndFilteredItemList.sortByDescending { it.priceWithDiscount }
                    itemsListAdapter?.submitList(sortedAndFilteredItemList)
                }

                else -> {
                    sortedAndFilteredItemList.sortBy { it.priceWithDiscount }
                    itemsListAdapter?.submitList(sortedAndFilteredItemList)
                }
            }
        }
    }

    private fun showMessage(stringId: Int) {
        Snackbar.make(
            binding.root,
            stringId,
            Snackbar.LENGTH_LONG
        ).show()
    }
}