package com.mvvm.example.views.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.example.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    fun loginWithCredentials(userName: String, password: String, loginResponseNew: (Any) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            Repository.instance
                .login(userName, password, {
                    viewModelScope.launch(Dispatchers.Main) {
                        loginResponseNew.invoke(it)
                    }
                }, {
                    viewModelScope.launch(Dispatchers.Main) {
                        loginResponseNew.invoke(it)
                    }
                }, {
                    viewModelScope.launch(Dispatchers.Main) {
                        loginResponseNew.invoke(it)
                    }
                })
        }
    }


}