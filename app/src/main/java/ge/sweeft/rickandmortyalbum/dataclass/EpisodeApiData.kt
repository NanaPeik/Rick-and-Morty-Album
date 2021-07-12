package ge.sweeft.rickandmortyalbum.dataclass

data class EpisodeApiData(
    val info: Info,
    val results: List<Episode>
)