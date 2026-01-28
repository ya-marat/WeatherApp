package com.example.weatherapp.data.network.api

import androidx.compose.ui.text.intl.Locale
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.Consts
import com.example.weatherapp.UrlLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object ApiFactory {

    private const val KEY_PARAM = "key"
    private const val PARAM_LANG = "lang"
    private const val BASE_URL = "https://api.weatherapi.com/v1/"
    private fun createRetrofit(): ApiService {

        val client = OkHttpClient().newBuilder()
        //client.connectTimeout(30, TimeUnit.SECONDS)
        //client.readTimeout(30, TimeUnit.SECONDS)
        //client.writeTimeout(30, TimeUnit.SECONDS)
        //
        //client.addInterceptor(UrlLoggingInterceptor())
        client.addInterceptor { chain ->
            val originalRequest = chain.request()
            val newUrl = originalRequest
                .url
                .newBuilder()
                .addQueryParameter(KEY_PARAM, BuildConfig.WEATHER_API_KEY)
                .addQueryParameter(PARAM_LANG, java.util.Locale.getDefault().language)
                .build()
            val newRequest = originalRequest.newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(newRequest)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()

        return retrofit.create()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient().newBuilder().addInterceptor(UrlLoggingInterceptor()).build())
        .build()

    val apiService: ApiService = createRetrofit()
}