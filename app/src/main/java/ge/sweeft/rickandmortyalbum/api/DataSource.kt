package ge.sweeft.rickandmortyalbum.api

import ge.sweeft.rickandmortyalbum.dataclass.Character
import ge.sweeft.rickandmortyalbum.dataclass.Episode
import ge.sweeft.rickandmortyalbum.dataclass.EpisodeApiData
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataSource @Inject constructor(var api: JsonApi) {
    suspend fun getAllEpisodes(): Response<EpisodeApiData> {
        return api.getAllEpisodes()
    }
    suspend fun getEpisodeById(id:Int):Response<Episode>{
        return api.getEpisodeById(id)
    }
    suspend fun getCharacterById(id:Int):Response<Character>{
        return api.getCharacterById(id)
    }
}