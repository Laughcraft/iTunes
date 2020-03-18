package com.laughcraft.android.itunes

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.google.gson.GsonBuilder
import com.laughcraft.android.itunes.data.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class App : Application() {
    
    companion object {
        const val API_ENDPOINT = "https://itunes.apple.com/"
        
        lateinit var instance: App
        lateinit var searchApi: Api
        
        fun appContext(): Context = instance.applicationContext
        @Suppress("DEPRECATION")
        fun isNetworkAvailable(): Boolean {
            val connectivityManager = appContext().getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities = connectivityManager.activeNetwork ?: return false
                val activeNetwork = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
                
                return when {activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    else -> false
                }
            }
            else {
                return connectivityManager.activeNetworkInfo != null &&
                        connectivityManager.activeNetworkInfo!!.isConnectedOrConnecting
            }
        }
    }
    
    override fun onCreate() {
        super.onCreate()
        instance = this
    
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    
        val client = OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor)
            .build()
    
        searchApi = Retrofit.Builder()
            .baseUrl(API_ENDPOINT)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()
            .create(Api::class.java)
    }
}