package ge.sweeft.rickandmortyalbum.api

import ge.sweeft.rickandmortyalbum.dataclass.Episode
import ge.sweeft.rickandmortyalbum.dataclass.EpisodeApiData
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EpisodeDataSource @Inject constructor(var api: JsonApi) {
    suspend fun getAllEpisodes(): Response<EpisodeApiData> {
        return api.getAllEpisodes()
    }
}