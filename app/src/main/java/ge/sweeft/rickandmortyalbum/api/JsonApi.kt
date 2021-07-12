package ge.sweeft.rickandmortyalbum.api

import ge.sweeft.rickandmortyalbum.dataclass.Character
import ge.sweeft.rickandmortyalbum.dataclass.Episode
import ge.sweeft.rickandmortyalbum.dataclass.EpisodeApiData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonApi {

    @GET("episode")
    suspend fun getAllEpisodes(): Response<EpisodeApiData>

    @GET("episode/{id}")
    suspend fun getEpisodeById(
        @Path("id") id: Int
    ): Response<Episode>
}