package net.simplifiedcoding.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.simplifiedcoding.data.network.Resource
import net.simplifiedcoding.data.repository.AuthRepository
import net.simplifiedcoding.data.responses.LoginResponseEmod
import net.simplifiedcoding.ui.base.BaseViewModel

class AuthViewModel(
    private val repository: AuthRepository
) : BaseViewModel(repository) {

    private val _loginResponse: MutableLiveData<Resource<LoginResponseEmod>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponseEmod>>
        get() = _loginResponse

    fun login(
        student_id: String,
        mobileno: String
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.login(student_id, mobileno)
    }

    suspend fun saveAuthToken(token: String) {
        repository.saveAuthToken(token)
    }
}