package ge.sweeft.rickandmortyalbum.character

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.sweeft.rickandmortyalbum.api.Repository
import ge.sweeft.rickandmortyalbum.dataclass.Episode
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(var repository: Repository) : ViewModel() {

    var episodeId = MutableLiveData<Int>()

    var response: LiveData<Episode?> = episodeId.switchMap {
        liveData(Dispatchers.IO) {
            val res = repository.getEpisodeById(it)
            emit(res)
        }
    }
}