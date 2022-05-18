package com.mvvm.example.repository.remote


import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class RemoteRepository {
    protected fun <T> create(clazz: Class<T>?, baseUrl: String): T {
        return retrofit(baseUrl).create(clazz)
    }

    private fun retrofit(baseUrl: String): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//        val sp = AppController.getInstance().prefManager
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .retryOnConnectionFailure(true)
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val original = chain.request()
                val request: Request.Builder = original.newBuilder()
                    .addHeader("Connection", "close")
                    .method(original.method, original.body)
                chain.proceed(request.build())
            })
            .connectionSpecs(ArrayList(Arrays.asList(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS)))
            .build()
        val gsonBuilder = GsonBuilder()
        val customGson = gsonBuilder.create()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(customGson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    suspend fun login(userName:String ,password:String): Response<ResponseBody> {
        return create(RemoteServices::class.java, BASE_URL).login(userName , password)
    }

    companion object {
        const val BASE_URL = "https://procuriot.ioptime.com/api/"
    }
}