package ge.sweeft.rickandmortyalbum.db.repository

import androidx.annotation.WorkerThread
import ge.sweeft.rickandmortyalbum.db.dao.CharacterDao
import ge.sweeft.rickandmortyalbum.db.entity.CharacterEntity
import ge.sweeft.rickandmortyalbum.db.entity.EpisodeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepo @Inject constructor(private val characterDao: CharacterDao) {

    val allCharacters: Flow<List<CharacterEntity>> = characterDao.getAllCharacters()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(character: CharacterEntity) {
        characterDao.insertCharacter(character)
    }
}