package com.example.movieshowstracker.di

import com.example.movieshowstracker.BuildConfig.BASE_URL
import com.example.movieshowstracker.constants.TIME_OUT
import com.example.movieshowstracker.data.services.MoviesService
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

val NetworkModule = module {


    single {

        val apiKeyInterceptor = object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val original: Request = chain.request()

                // Request customization: add request headers
                val finalURL = original.url.toString() +"&apikey=30083488"
                val requestBuilder: Request.Builder = original.newBuilder().url(finalURL)
                    .header("Authorization", "MY_API_KEY") // <-- this is the important line
                val request: Request = requestBuilder.build()
                return chain.proceed(request)
            }
        }

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor(apiKeyInterceptor)
            .build()

        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL).build()
    }
    factory{ get<Retrofit>().create(MoviesService::class.java) }
}