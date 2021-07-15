package ge.sweeft.rickandmortyalbum.db.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ge.sweeft.rickandmortyalbum.db.EpisodeDatabase
import ge.sweeft.rickandmortyalbum.db.dao.EpisodeDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideEpisodeDao(episodeDatabase: EpisodeDatabase): EpisodeDao {
        return episodeDatabase.episodeDao()
    }
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): EpisodeDatabase {
        return Room.databaseBuilder(
            appContext,
            EpisodeDatabase::class.java,
            "RssReader"
        ).addMigrations(EpisodeDatabase.MIGRATION_2_3).build()
//        fallbackToDestructiveMigration()
    }
//    @Provides
//    fun provideCharacterDao(episodeDatabase: EpisodeDatabase): CharacterDao {
//        return episodeDatabase.characterDao()
//    }

}