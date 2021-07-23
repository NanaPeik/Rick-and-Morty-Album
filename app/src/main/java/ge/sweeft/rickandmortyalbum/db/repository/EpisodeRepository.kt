package ge.sweeft.rickandmortyalbum.db.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import ge.sweeft.rickandmortyalbum.db.dao.EpisodeDao
import ge.sweeft.rickandmortyalbum.db.entity.EpisodeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodeRepository @Inject constructor(private val episodeDao: EpisodeDao) {

    val allEpisode: LiveData<List<EpisodeEntity>> = episodeDao.getAllEpisode()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(episodeEntity: EpisodeEntity) {
        episodeDao.insertEpisode(episodeEntity)
    }
}