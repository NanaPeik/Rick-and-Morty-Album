package ge.sweeft.rickandmortyalbum

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ge.sweeft.rickandmortyalbum.api.Repository
import ge.sweeft.rickandmortyalbum.db.EpisodeDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class AlbumApplication : Application() {
}