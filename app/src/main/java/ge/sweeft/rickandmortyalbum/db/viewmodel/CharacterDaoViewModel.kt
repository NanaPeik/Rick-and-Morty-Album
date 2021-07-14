package ge.sweeft.rickandmortyalbum.db.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.sweeft.rickandmortyalbum.db.entity.CharacterEntity
import ge.sweeft.rickandmortyalbum.db.repository.CharacterRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDaoViewModel @Inject constructor(private val repository:CharacterRepo):ViewModel() {

    val allCharacter: LiveData<List<CharacterEntity>> = repository.allCharacters.asLiveData()

    fun insert(character: CharacterEntity) = viewModelScope.launch {
        repository.insert(character)
    }

}