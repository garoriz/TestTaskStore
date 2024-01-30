package com.garif.teststore.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garif.database.model.User
import com.garif.teststore.domain.usecase.SaveUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {
    private var _error: MutableLiveData<Exception> = MutableLiveData()
    val error: LiveData<Exception> = _error

    fun onSaveUser(user: User) {
        viewModelScope.launch {
            try {
                saveUserUseCase(user)
            } catch (ex: Exception) {
                _error.value = ex
            }
        }
    }
}