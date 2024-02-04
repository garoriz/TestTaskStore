package com.garif.favorites_feature.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.addisonelliott.segmentedbutton.SegmentedButtonGroup
import com.garif.core.navigation.navigate
import com.garif.core.util.AppViewModelFactory
import com.garif.favorites_feature.R
import com.garif.favorites_feature.databinding.FragmentFavoritesBinding
import com.garif.favorites_feature.di.FavoritesFeatureComponentProvider
import com.garif.favorites_feature.domain.entity.Item
import com.garif.favorites_feature.presentation.adapter.items.ItemListAdapter
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    @Inject
    lateinit var factory: AppViewModelFactory
    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModels {
        factory
    }
    private var itemsListAdapter: ItemListAdapter? = null
    private val itemList: MutableList<Item> = mutableListOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as FavoritesFeatureComponentProvider)
            .getFavoritesFeatureComponent()
            .injectFavoritesFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFavoritesBinding.bind(view)

        with(binding) {
            productsOrBrandsButtonGroup.onPositionChangedListener =
                SegmentedButtonGroup.OnPositionChangedListener {
                    setDataBySwitchersPosition()
                }

            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        initObservers()
        viewModel.onGetLikedItemIds()
    }

    private fun setDataBySwitchersPosition() {
        with(binding) {
            when (productsOrBrandsButtonGroup.position) {
                0 -> items.isVisible = true

                else -> items.isVisible = false
            }
        }
    }


    private fun initObservers() {
        viewModel.likedItemIds.observe(viewLifecycleOwner) { it ->
            it.fold(onSuccess = {
                viewModel.onGetLikedItems(it)
            }, onFailure = {
                Log.e("e", it.message.toString())
            })
        }

        viewModel.likedItems.observe(viewLifecycleOwner) { it ->
            it.fold(onSuccess = { items ->
                itemsListAdapter = ItemListAdapter({
                    navigate(R.id.action_favoritesFragment_to_itemFragment, data = it)
                }, {
                    viewModel.onDeleteLikedItem(com.garif.database.model.Item(it))
                    submitNewItemListInAdapter(it)
                }, this@FavoritesFragment)

                binding.items.run {
                    adapter = itemsListAdapter
                }

                itemList.clear()
                itemList.addAll(items)
                itemsListAdapter?.submitList(itemList)
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

    private fun submitNewItemListInAdapter(deletedItemId: String) {
        var i = 0
        while (i < itemList.size && itemList[i].id != deletedItemId)
            i++
        itemList.removeAt(i)
        itemsListAdapter?.submitList(itemList)
    }

    private fun showMessage(stringId: Int) {
        Snackbar.make(
            binding.root,
            stringId,
            Snackbar.LENGTH_LONG
        ).show()
    }
}