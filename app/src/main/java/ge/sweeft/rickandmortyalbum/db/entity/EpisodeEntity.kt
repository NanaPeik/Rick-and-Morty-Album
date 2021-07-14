package ge.sweeft.rickandmortyalbum.db.entity

import androidx.annotation.NonNull
import androidx.room.*
import ge.sweeft.rickandmortyalbum.db.converter.Converters

@Entity(tableName = "episode")
data class EpisodeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    val id: Int,
    val air_date: String,
    @TypeConverters(Converters::class)
    val characters: List<String>,
    val created: String,
    val episode: String,
    val name: String,
    val url: String
)