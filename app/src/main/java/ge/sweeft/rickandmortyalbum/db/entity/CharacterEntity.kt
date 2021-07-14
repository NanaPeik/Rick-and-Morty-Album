package ge.sweeft.rickandmortyalbum.db.entity

import androidx.annotation.NonNull
import androidx.room.*
import ge.sweeft.rickandmortyalbum.dataclass.Location
import ge.sweeft.rickandmortyalbum.dataclass.Origin
import ge.sweeft.rickandmortyalbum.db.converter.StringListConverters

@Entity(tableName = "character")
data class CharacterEntity(
    val created: String,
    @TypeConverters(StringListConverters::class)
    val episode: List<String>,
    val gender: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    val id: Int,
    val image: String,
    @Embedded
    val location: Location,
    val name: String,
    @Embedded
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
