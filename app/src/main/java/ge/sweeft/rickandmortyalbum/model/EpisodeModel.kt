package ge.sweeft.rickandmortyalbum.model

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.sweeft.rickandmortyalbum.api.BaseUrl
import ge.sweeft.rickandmortyalbum.api.JsonApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object EpisodeModel {
    @Provides
    fun provideEpisodeData(): JsonApi {
        return Retrofit.Builder()
            .baseUrl(BaseUrl.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create()
    }
}