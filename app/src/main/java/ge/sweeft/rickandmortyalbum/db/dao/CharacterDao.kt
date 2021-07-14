package ge.sweeft.rickandmortyalbum.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ge.sweeft.rickandmortyalbum.db.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character ORDER BY name ASC")
    fun getAllCharacters(): Flow<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacter(characterEntity: CharacterEntity)
}