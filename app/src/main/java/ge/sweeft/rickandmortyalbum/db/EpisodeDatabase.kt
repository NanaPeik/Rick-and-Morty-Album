package ge.sweeft.rickandmortyalbum.db

import androidx.room.*
import ge.sweeft.rickandmortyalbum.db.converter.Converters
import ge.sweeft.rickandmortyalbum.db.dao.EpisodeDao
import ge.sweeft.rickandmortyalbum.db.entity.EpisodeEntity

@Database(entities = [EpisodeEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class EpisodeDatabase:RoomDatabase() {
    abstract fun episodeDao():EpisodeDao
}