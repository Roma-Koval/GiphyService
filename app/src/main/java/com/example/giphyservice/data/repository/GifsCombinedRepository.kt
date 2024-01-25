package com.example.giphyservice.data.repository

import com.example.giphyservice.data.repository.RepositoryResult.Error
import com.example.giphyservice.data.repository.RepositoryResult.Success
import com.example.giphyservice.ui.Gif

class GifsCombinedRepository(
    private val remote: RemoteGifsRepository,
    private val local: LocalGifsRepository
) : GifsRepository {

    override suspend fun getGifsData(): RepositoryResult<List<Gif>> {
        return when (val result = local.getGifsData()) {
            is Error -> loadRemote()
            is Success -> {
                if (result.dataResult.isEmpty()) {
                    loadRemote()
                } else {
                    result
                }
            }
        }
    }

    private suspend fun loadRemote() =
        when (val remoteResult = remote.getGifsData()) {
            is Error -> remoteResult
            is Success -> {
                local.saveGifs(remoteResult.dataResult)
                remoteResult
            }
        }
}