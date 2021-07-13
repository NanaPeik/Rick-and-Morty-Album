package ge.sweeft.rickandmortyalbum.api

import javax.inject.Inject

class Repository @Inject constructor(
    var dataSource: DataSource
) {
    suspend fun getAllEpisodes() = dataSource.getAllEpisodes().body()

    suspend fun getEpisodeById(id: Int) = dataSource.getEpisodeById(id).body()

    suspend fun getCharacterById(id: Int) = dataSource.getCharacterById(id).body()
}