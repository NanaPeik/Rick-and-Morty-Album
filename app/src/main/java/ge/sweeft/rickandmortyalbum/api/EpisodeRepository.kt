package ge.sweeft.rickandmortyalbum.api

import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    var episodeDataSource: EpisodeDataSource
) {
    suspend fun getAllEpisodes() = episodeDataSource.getAllEpisodes().body()
}