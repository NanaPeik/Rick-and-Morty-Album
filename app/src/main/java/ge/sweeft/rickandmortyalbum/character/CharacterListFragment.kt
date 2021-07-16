package ge.sweeft.rickandmortyalbum.character

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ge.sweeft.rickandmortyalbum.databinding.FragmentCharacterListBinding
import ge.sweeft.rickandmortyalbum.dataclass.Character
import ge.sweeft.rickandmortyalbum.episode.EpisodeViewModel

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding
    private lateinit var characterAdapter: CharacterAdapter
    private val episodeViewModel: EpisodeViewModel by viewModels()
    private val characterViewModel: CharacterViewModel by viewModels()
    private val args: CharacterListFragmentArgs by navArgs()

    private val countDownMilesInFuture: Long = 2000
    private val countDownInterval: Long = 1000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        episodeViewModel.episodeId.value = args.episodeId
        episodeViewModel.episodeResponseById.observe(viewLifecycleOwner, {
            if (it != null) {
                getCharactersApi(it.characters)
                binding.episodeDataCharacter.text = it.air_date
                binding.episodeNameCharacter.text = it.name
                binding.episodeEpisodeCharacter.text = it.episode

            }
        })

        searchCharacters()
    }

    private fun searchCharacters() {
        binding.searchCharacter.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchCharacter.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                val timer = object : CountDownTimer(countDownMilesInFuture, countDownInterval) {
                    override fun onTick(millisUntilFinished: Long) {}
                    override fun onFinish() {
                        newText?.let {
                            characterViewModel.searchCharacter(newText)
                        }
                        characterViewModel.filteredCharacters.observe(viewLifecycleOwner, {
                            if (it.isEmpty()) {
                                binding.emptyCharactersPage.visibility = View.VISIBLE
                            } else {
                                binding.emptyCharactersPage.visibility = View.GONE
                            }
                            setCharacterAdapter(it)
                            characterAdapter.notifyDataSetChanged()
                        })
                    }
                }
                timer.start()
                return true
            }
        })
    }

    private fun getCharactersApi(charactersUrl: List<String>) {
        characterViewModel.getCharacters(charactersUrl)
        characterViewModel.characterResponse.observe(viewLifecycleOwner, {
            setCharacterAdapter(it)
            this.characterAdapter.notifyDataSetChanged()
        })
    }

    private fun setCharacterAdapter(characters: List<Character>) {
        this.characterAdapter = CharacterAdapter(characters)

        binding.characterRecycler.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.characterRecycler.adapter = characterAdapter
    }
}