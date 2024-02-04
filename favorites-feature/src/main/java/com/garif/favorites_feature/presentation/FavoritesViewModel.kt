package com.garif.favorites_feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garif.database.model.Item
import com.garif.favorites_feature.domain.usecase.DeleteLikedItemUseCase
import com.garif.favorites_feature.domain.entity.Item as EntityItem
import com.garif.favorites_feature.domain.usecase.GetLikedItemIdsUseCase
import com.garif.favorites_feature.domain.usecase.GetLikedItemsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val getLikedItemIdsUseCase: GetLikedItemIdsUseCase,
    private val getLikedItemsUseCase: GetLikedItemsUseCase,
    private val deleteLikedItemUseCase: DeleteLikedItemUseCase,
) : ViewModel() {
    private var _likedItemIds: MutableLiveData<Result<List<Item>>> = MutableLiveData()
    val likedItemIds: LiveData<Result<List<Item>>> = _likedItemIds

    private var _likedItems: MutableLiveData<Result<List<EntityItem>>> = MutableLiveData()
    val likedItems: LiveData<Result<List<EntityItem>>> = _likedItems

    private var _error: MutableLiveData<Exception> = MutableLiveData()
    val error: LiveData<Exception> = _error

    fun onGetLikedItemIds() {
        viewModelScope.launch {
            try {
                val likedItemsIds = getLikedItemIdsUseCase()
                _likedItemIds.value = Result.success(likedItemsIds)
            } catch (ex: Exception) {
                _likedItemIds.value = Result.failure(ex)
                _error.value = ex
            }
        }
    }

    fun onGetLikedItems(itemIds: List<Item>) {
        viewModelScope.launch {
            try {
                val likedItems = getLikedItemsUseCase(itemIds)
                _likedItems.value = Result.success(likedItems)
            } catch (ex: Exception) {
                _likedItems.value = Result.failure(ex)
                _error.value = ex
            }
        }
    }

    fun onDeleteLikedItem(item: Item) {
        viewModelScope.launch {
            try {
                deleteLikedItemUseCase(item)
            } catch (ex: Exception) {
                _error.value = ex
            }
        }
    }
}