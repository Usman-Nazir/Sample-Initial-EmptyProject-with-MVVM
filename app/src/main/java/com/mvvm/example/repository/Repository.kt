package com.mvvm.example.repository

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.gson.Gson
import com.mvvm.example.common.AppController
import com.mvvm.example.models.generalError.ModelError
import com.mvvm.example.models.userLogin.ModelLogin
import com.mvvm.example.repository.local.LocalRepository
import com.mvvm.example.repository.remote.RemoteRepository
import com.mvvm.example.utilities.showSnackBar
import kotlinx.coroutines.delay
import org.json.JSONObject
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class Repository(remoteRepository: RemoteRepository?, localRepository: LocalRepository?) {
    val localRepository: LocalRepository?
        get() = Companion.localRepository

    val remoteRepository: RemoteRepository?
        get() = Companion.remoteRepository

    suspend fun login(userName: String, password: String, success: (ModelLogin) -> Unit, errorCallback: (ModelError) -> Unit, exceptionCallback: (e: Exception) -> Unit) {
        try {
            val response = remoteRepository?.login(userName, password)
            delay(5000)
            success.invoke(ModelLogin("dsfdsfdsfsdfsdfdf44f" ,"12/12/2020",""))
            return
            if (response?.isSuccessful == true) {
                try {
                    val data = Gson().fromJson(response.body()?.string(), ModelLogin::class.java)
                    success.invoke(data)
                } catch (e: Exception) {
                    exceptionCallback.invoke(e)
                }
            } else {
                try {
                    val errorBody = response?.errorBody()?.string().toString()
                    val obj = JSONObject(errorBody)
                    val error = ModelError()
                    error.message = obj.optString("message", "")
                    val errors :MutableList<String> = arrayListOf()
                    obj.optJSONArray("errors")?.let {arrayJson->
                        for (i in 0 until arrayJson.length()){
                            arrayJson.optJSONObject(i)?.optString("message")?.let {
                                errors.add(it)
                            }
                        }
                    }
                    error.errors = errors
                    errorCallback.invoke(error)
                } catch (e: Exception) {
                    exceptionCallback.invoke(e)
                }
            }
        } catch (e: Exception) {
            exceptionCallback.invoke(e)
        }
    }


    /**** common network handling ******/
    fun handleNetworkErrors(e: Exception, context_: Activity) {
        Log.i("test", " e $e")
        Handler(Looper.getMainLooper()).post {
            when (e) {
                is UnknownHostException, is NoRouteToHostException -> context_.showSnackBar("Please check internet connections")
                is SocketTimeoutException -> context_.showSnackBar("Connection time out, please try again")
                else -> context_.showSnackBar("Oops other error we are checking")
            }
        }
    }

    companion object {
        const val TAG = "Repository"
        var ROOT_PATH: String? = null
        var remoteRepository: RemoteRepository? = null
        var localRepository: LocalRepository? = null
        val instance: Repository
            get() = AppController.getRepository()
    }

    init {
        Companion.remoteRepository = remoteRepository
        Companion.localRepository = localRepository
    }
}