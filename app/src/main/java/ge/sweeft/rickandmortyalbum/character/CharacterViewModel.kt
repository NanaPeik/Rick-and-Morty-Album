package ge.sweeft.rickandmortyalbum.character

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.sweeft.rickandmortyalbum.api.Repository
import ge.sweeft.rickandmortyalbum.dataclass.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(var repository: Repository) : ViewModel() {

    var characterResponse = MutableLiveData<List<Character>>()

    fun getCharacters(charactersUrl: List<String>) {
        var characters = mutableListOf<Character>()

        viewModelScope.launch {
            val baseString = "https://rickandmortyapi.com/api/character/"
            for (url in charactersUrl) {
                val id = Integer.parseInt(url.subSequence(baseString.length, url.length).toString())
                val character = repository.getCharacterById(id)
                character?.let { characters.add(character) }
            }
            characterResponse.value = characters
        }
    }

    var characterId = MutableLiveData<Int>()
    var characterResponseId: LiveData<Character?> = characterId.switchMap {
        liveData(Dispatchers.IO) {
            val res = repository.getCharacterById(it)
            emit(res)
        }
    }

    var filteredCharacters = MutableLiveData<List<Character>>()

    private var searchJob: Job? = null

    fun searchCharacter(characterName: String) {
        searchJob?.cancel()
        searchJob=viewModelScope.launch {
            delay(1000)
            val charactersList = characterResponse.value
            val filteredList = arrayListOf<Character>()
            if (charactersList != null) {
                for (character in charactersList) {
                    if (character.name.contains(characterName)) {
                        filteredList.add(character)
                    }
                }
                filteredCharacters.value = filteredList
            }
        }

    }

}