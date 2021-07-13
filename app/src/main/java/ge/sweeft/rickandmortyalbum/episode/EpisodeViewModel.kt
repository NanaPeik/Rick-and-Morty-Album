package ge.sweeft.rickandmortyalbum.episode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ge.sweeft.rickandmortyalbum.api.Repository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.sweeft.rickandmortyalbum.dataclass.Episode
import ge.sweeft.rickandmortyalbum.dataclass.EpisodeApiData
import kotlinx.coroutines.launch

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    var repository: Repository
) : ViewModel() {
    var episode = MutableLiveData<String>()

    var episodesResponse:LiveData<EpisodeApiData?> = episode.switchMap {
        liveData(Dispatchers.IO) {
            val res = repository.getAllEpisodes()
            emit(res)
        }
    }

    var episodeId = MutableLiveData<Int>()

    var episodeResponseById: LiveData<Episode?> = episodeId.switchMap {
        liveData(Dispatchers.IO) {
            val res = repository.getEpisodeById(it)
            emit(res)
        }
    }

    var episodesByCharacterResponse = MutableLiveData<List<Episode>>()

    fun getEpisodesByCharacter(episodeUrl: List<String>) {
        var episodes = mutableListOf<Episode>()

        viewModelScope.launch {
            val baseString="https://rickandmortyapi.com/api/episode/"
            for (url in episodeUrl) {
                val id = Integer.parseInt(url.subSequence(baseString.length, url.length).toString())
                val episode = repository.getEpisodeById(id)
                episode?.let { episodes.add(episode) }
            }
            episodesByCharacterResponse.value = episodes
        }
    }
}