package com.example.giphyservice.di

import com.example.giphyservice.data.GifService
import com.example.giphyservice.data.repository.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    // @Provides tell Dagger how to create instances of the type that this function
    // returns
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideGifService(retrofit: Retrofit): GifService {
        return retrofit.create(GifService::class.java)
    }
}