package com.example.giphyservice.data.repository

import com.example.giphyservice.data.model.Gif
import java.time.Duration
import java.time.LocalDateTime

class GifsCombinedRepository(
    private val remoteGifsRepository: GifsRepositoryImp,
    private val localGifsRepository: GifsDBRepository
) : GifsRepository {

    override suspend fun getGifsData(): RepositoryResult<Gif> {
        if (localGifsRepository.getAllGifs().isEmpty()) {
            localGifsRepository.getAndSaveGifs()
        }

        val lastUpdate = localGifsRepository.getLastUpdate()

        if (Duration.between(lastUpdate, LocalDateTime.now()) < Duration.ofDays(1)) {
            return localGifsRepository.getGifsData()
        } else {
            val remoteResult = remoteGifsRepository.getGifsData()
            if (remoteResult is RepositoryResult.Success) {
                localGifsRepository.deleteGifs()
                localGifsRepository.getAndSaveGifs()
            }
        }
        return localGifsRepository.getGifsData()
    }
}