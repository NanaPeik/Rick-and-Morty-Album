package ge.sweeft.rickandmortyalbum.db

import androidx.room.*
import ge.sweeft.rickandmortyalbum.db.converter.StringListConverters
import ge.sweeft.rickandmortyalbum.db.dao.EpisodeDao
import ge.sweeft.rickandmortyalbum.db.entity.EpisodeEntity
import ge.sweeft.rickandmortyalbum.db.migration.Migration2to3

@Database(
    entities = [EpisodeEntity::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(StringListConverters::class)
abstract class EpisodeDatabase : RoomDatabase() {
    companion object {
        @JvmField
        val MIGRATION_2_3 = Migration2to3()
    }
    abstract fun episodeDao(): EpisodeDao

}