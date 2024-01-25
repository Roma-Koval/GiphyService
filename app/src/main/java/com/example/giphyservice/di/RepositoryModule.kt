package com.example.giphyservice.di

import com.example.giphyservice.data.GifService
import com.example.giphyservice.data.repository.GifsCombinedRepository
import com.example.giphyservice.data.repository.LocalGifsRepository
import com.example.giphyservice.data.repository.RemoteGifsRepository
import com.example.giphyservice.data.repository.GifsRepository
import com.example.giphyservice.data.room.dao.GifDAO
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideCombinedRepository(
        remoteGifsRepository: RemoteGifsRepository,
        localGifsRepository: LocalGifsRepository
    ): GifsRepository = GifsCombinedRepository(remoteGifsRepository, localGifsRepository)

    @Provides
    fun provideLocalRepository(gifDAO: GifDAO): LocalGifsRepository {
        return LocalGifsRepository(gifDAO)
    }

    @Provides
    fun provideRemoteGifsRepository(gifService: GifService): RemoteGifsRepository {
        return RemoteGifsRepository(gifService)
    }
}