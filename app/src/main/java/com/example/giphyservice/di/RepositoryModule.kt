package com.example.giphyservice.di

import com.example.giphyservice.data.GifService
import com.example.giphyservice.data.repository.GifsDBRepository
import com.example.giphyservice.data.repository.GifsRepositoryImp
import com.example.giphyservice.data.repository.GifsRepository
import com.example.giphyservice.data.room.database.GifDatabase
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
//    @Provides
//    fun provideRepository(gifService: GifService): GifsRepository {
//        return GifsRepositoryImp(gifService)
//    }

    @Provides
    fun provideDBRepository(gifService: GifService, database: GifDatabase) : GifsRepository {
        return GifsDBRepository(gifService, database)
    }
}