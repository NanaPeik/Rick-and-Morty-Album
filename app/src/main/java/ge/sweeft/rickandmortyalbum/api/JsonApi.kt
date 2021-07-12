package ge.sweeft.rickandmortyalbum.api

import ge.sweeft.rickandmortyalbum.dataclass.Episode
import ge.sweeft.rickandmortyalbum.dataclass.EpisodeApiData
import retrofit2.Response
import retrofit2.http.GET

interface JsonApi {

    @GET("episode")
    suspend fun getAllEpisodes():Response<EpisodeApiData>
}