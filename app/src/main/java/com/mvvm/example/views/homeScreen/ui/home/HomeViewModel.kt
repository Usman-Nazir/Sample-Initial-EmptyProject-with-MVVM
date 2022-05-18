package com.mvvm.example.views.homeScreen.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    fun getLocation(long:String ,lat:String , callBackResponse: (Any) -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
//            Repository.instance
//                .getLocation(  long ,lat  ,{
//                    viewModelScope.launch(Dispatchers.Main) {
//                        callBackResponse.invoke(it)
//                    }
//                }, {
//                    viewModelScope.launch(Dispatchers.Main) {
//                        callBackResponse.invoke(it)
//                    }
//                }, {
//                    viewModelScope.launch(Dispatchers.Main) {
//                        callBackResponse.invoke(it)
//                    }
//                })
        }
    }

}