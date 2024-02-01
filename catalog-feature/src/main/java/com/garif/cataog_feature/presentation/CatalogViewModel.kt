package com.garif.cataog_feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garif.cataog_feature.domain.entity.Item as EntityItem
import com.garif.database.model.Item
import com.garif.cataog_feature.domain.usecase.GetItemsUseCase
import com.garif.cataog_feature.domain.usecase.SaveLikedItemUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    private val saveLikedItemUseCase: SaveLikedItemUseCase,
    private val getItemsUseCase: GetItemsUseCase,
) : ViewModel() {
    private var _items: MutableLiveData<Result<MutableList<EntityItem>>> = MutableLiveData()
    val items: LiveData<Result<MutableList<EntityItem>>> = _items

    private var _error: MutableLiveData<Exception> = MutableLiveData()
    val error: LiveData<Exception> = _error

    fun onGetItems() {
        viewModelScope.launch {
            try {
                val items = getItemsUseCase() as MutableList<EntityItem>
                _items.value = Result.success(items)
            } catch (ex: Exception) {
                _items.value = Result.failure(ex)
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