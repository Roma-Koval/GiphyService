package com.example.giphyservice.di

import com.example.giphyservice.data.GifService
import com.example.giphyservice.data.repository.GifsRepositoryImp
import com.example.giphyservice.data.repository.GifsRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(gifService: GifService): GifsRepository {
        return GifsRepositoryImp(gifService)
    }
}