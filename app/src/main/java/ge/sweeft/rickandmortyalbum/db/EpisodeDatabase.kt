package ge.sweeft.rickandmortyalbum.db

import androidx.room.*
import ge.sweeft.rickandmortyalbum.db.converter.StringListConverters
import ge.sweeft.rickandmortyalbum.db.dao.CharacterDao
import ge.sweeft.rickandmortyalbum.db.dao.EpisodeDao
import ge.sweeft.rickandmortyalbum.db.entity.CharacterEntity
import ge.sweeft.rickandmortyalbum.db.entity.EpisodeEntity

@Database(
    entities = [EpisodeEntity::class, CharacterEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(StringListConverters::class)
abstract class EpisodeDatabase : RoomDatabase() {
    abstract fun episodeDao(): EpisodeDao
    abstract fun characterDao(): CharacterDao
}