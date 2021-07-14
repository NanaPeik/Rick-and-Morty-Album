package ge.sweeft.rickandmortyalbum.dataclass

import androidx.room.ColumnInfo

data class Origin(
    @ColumnInfo(name = "origin_name")
    val name: String,
    @ColumnInfo(name = "origin_url")
    val url: String
)