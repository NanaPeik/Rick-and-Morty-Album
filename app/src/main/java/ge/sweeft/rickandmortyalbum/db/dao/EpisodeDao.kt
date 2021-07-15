package ge.sweeft.rickandmortyalbum.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ge.sweeft.rickandmortyalbum.db.entity.EpisodeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodeDao {
    @Query("SELECT * FROM episode ORDER BY RANDOM()")
    fun getAllEpisode(): Flow<List<EpisodeEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEpisode(episodeEntity: EpisodeEntity)
}