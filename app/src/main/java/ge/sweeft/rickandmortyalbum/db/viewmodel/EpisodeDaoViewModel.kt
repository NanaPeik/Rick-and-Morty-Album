package ge.sweeft.rickandmortyalbum.db.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.sweeft.rickandmortyalbum.db.entity.EpisodeEntity
import ge.sweeft.rickandmortyalbum.db.repository.EpisodeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeDaoViewModel @Inject constructor(private val repository: EpisodeRepository) :
    ViewModel() {
    val allEpisode: LiveData<List<EpisodeEntity>> = repository.allEpisode

    fun insert(episodeEntity: EpisodeEntity) = viewModelScope.launch {
        repository.insert(episodeEntity)
    }
}