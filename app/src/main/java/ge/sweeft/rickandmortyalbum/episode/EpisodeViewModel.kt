package ge.sweeft.rickandmortyalbum.episode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ge.sweeft.rickandmortyalbum.api.Repository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.sweeft.rickandmortyalbum.dataclass.EpisodeApiData

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    var repository: Repository
) : ViewModel() {
    var episode = MutableLiveData<String>()

    var response:LiveData<EpisodeApiData?> = episode.switchMap {
        liveData(Dispatchers.IO) {
            val res = repository.getAllEpisodes()
            emit(res)
        }
    }
}