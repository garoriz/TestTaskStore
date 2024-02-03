package com.garif.personal_profile_feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garif.database.model.Item
import com.garif.database.model.User
import com.garif.personal_profile_feature.domain.DeleteAllInDbUseCase
import com.garif.personal_profile_feature.domain.GetLikedItemsUseCase
import com.garif.personal_profile_feature.domain.GetUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonalProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getLikedItemsUseCase: GetLikedItemsUseCase,
    private val deleteAllInDbUseCase: DeleteAllInDbUseCase,
) : ViewModel() {
    private var _user: MutableLiveData<Result<User?>> = MutableLiveData()
    val user: LiveData<Result<User?>> = _user

    private var _likedItems: MutableLiveData<Result<List<Item>>> = MutableLiveData()
    val likedItems: LiveData<Result<List<Item>>> = _likedItems

    private var _error: MutableLiveData<Exception> = MutableLiveData()
    val error: LiveData<Exception> = _error

    fun onGetUser() {
        viewModelScope.launch {
            try {
                val user = getUserUseCase()
                _user.value = Result.success(user)
            } catch (ex: Exception) {
                _user.value = Result.failure(ex)
                _error.value = ex
            }
        }
    }

    fun onGetLikedItem() {
        viewModelScope.launch {
            try {
                val likedItems = getLikedItemsUseCase()
                _likedItems.value = Result.success(likedItems)
            } catch (ex: Exception) {
                _likedItems.value = Result.failure(ex)
                _error.value = ex
            }
        }
    }

    fun onDeleteAllInDb() {
        viewModelScope.launch {
            try {
                deleteAllInDbUseCase()
            } catch (ex: Exception) {
                _error.value = ex
            }
        }
    }
}