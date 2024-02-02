package com.garif.item_feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garif.database.model.Item
import com.garif.item_feature.domain.usecase.GetItemUseCase
import com.garif.item_feature.domain.entity.Item as EntityItem
import com.garif.item_feature.domain.usecase.GetLikedItemUseCase
import com.garif.item_feature.domain.usecase.SaveLikedItemUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ItemViewModel @Inject constructor(
    private val saveLikedItemUseCase: SaveLikedItemUseCase,
    private val getLikedItemUseCase: GetLikedItemUseCase,
    private val getItemUseCase: GetItemUseCase,
) : ViewModel() {
    private var _likedItem: MutableLiveData<Result<Item?>> = MutableLiveData()
    val likedItem: LiveData<Result<Item?>> = _likedItem

    private var _item: MutableLiveData<Result<EntityItem?>> = MutableLiveData()
    val item: LiveData<Result<EntityItem?>> = _item

    private var _error: MutableLiveData<Exception> = MutableLiveData()
    val error: LiveData<Exception> = _error

    fun onGetLikedItem(id: String) {
        viewModelScope.launch {
            try {
                val items = getLikedItemUseCase(id)
                _likedItem.value = Result.success(items)
            } catch (ex: Exception) {
                _likedItem.value = Result.failure(ex)
                _error.value = ex
            }
        }
    }

    fun onGetItem(id: String) {
        viewModelScope.launch {
            try {
                val item = getItemUseCase(id)
                _item.value = Result.success(item)
            } catch (ex: Exception) {
                _item.value = Result.failure(ex)
                _error.value = ex
            }
        }
    }

    fun onSaveItem(item: Item) {
        viewModelScope.launch {
            try {
                saveLikedItemUseCase(item)
            } catch (ex: Exception) {
                _error.value = ex
            }
        }
    }
}