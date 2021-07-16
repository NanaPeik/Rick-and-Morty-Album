package ge.sweeft.rickandmortyalbum.episode

import android.os.Bundle
import android.os.CountDownTimer
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import ge.sweeft.rickandmortyalbum.MainViewModel
import ge.sweeft.rickandmortyalbum.character.CharacterViewModel
import ge.sweeft.rickandmortyalbum.databinding.FragmentEpisodeListBinding
import ge.sweeft.rickandmortyalbum.dataclass.Episode
import ge.sweeft.rickandmortyalbum.db.viewmodel.EpisodeDaoViewModel
import ge.sweeft.rickandmortyalbum.db.entity.EpisodeEntity

@AndroidEntryPoint
class EpisodeListFragment : Fragment(), EpisodesAdapter.CustomClickListener {

    private val episodeDaoViewModel: EpisodeDaoViewModel by viewModels()
    private val episodeViewModel: EpisodeViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private val characterViewModel: CharacterViewModel by viewModels()
    private lateinit var binding: FragmentEpisodeListBinding
    private lateinit var episodesAdapter: EpisodesAdapter
    private val args: EpisodeListFragmentArgs by navArgs()

    private val countDownMilesInFuture: Long = 2000
    private val countDownInterval: Long = 1000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (args.characterId != 0) {
            characterViewModel.characterId.value = args.characterId
            characterViewModel.characterResponseId.observe(viewLifecycleOwner, {
                it?.let {
                    getEpisodesUrl(it.episode)
                    binding.characterInfo.visibility = View.VISIBLE
                    Picasso.get().load(it.image).into(binding.characterDetailImage)
                    binding.characterDetailName.text = it.name
                    binding.characterDetailStatus.text = it.status
                    binding.characterGender.text = it.gender
                    binding.characterOriginName.text = it.origin.name
                }
            })
        } else {
            episodeDaoViewModel.allEpisode.observe(viewLifecycleOwner, {
                var episodes = arrayListOf<Episode>()
                if (it != null) {
                    for (episode in it) {
                        episodes.add(
                            Episode(
                                episode.air_date,
                                episode.characters,
                                episode.created,
                                episode.episode,
                                episode.id,
                                episode.name,
                                episode.url
                            )
                        )
                        setEpisodesAdapter(episodes)
                    }

                }
            })
            episodeViewModel.episode.value = ""
            episodeViewModel.episodesResponse.observe(viewLifecycleOwner, {
                if (it != null) {
                    for (episode in it.results) {
                        episodeDaoViewModel.insert(
                            EpisodeEntity(
                                episode.id,
                                episode.air_date,
                                episode.characters,
                                episode.created,
                                episode.episode,
                                episode.name,
                                episode.url
                            )
                        )
                    }
                    this.episodesAdapter = EpisodesAdapter(
                        it.results,
                        this
                    )
                    episodesAdapter.notifyDataSetChanged()
                }
            })
        }

        searchEpisodes()

    }

    private fun searchEpisodes() {
        binding.searchEpisode.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchEpisode.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                val timer = object : CountDownTimer(countDownMilesInFuture, countDownInterval) {
                    override fun onTick(millisUntilFinished: Long) {}

                    override fun onFinish() {
                        if (newText != null) {
                            if (mainViewModel.firstScreen.value == true) {
                                episodeViewModel.filteredAllEpisodes(newText)
                            } else {
                                episodeViewModel.filteredEpisodes(newText)
                            }
                            episodeViewModel.filteredEpisodes.observe(viewLifecycleOwner, {
                                if (it.isEmpty()) {
                                    binding.emptyCharactersPage.visibility = View.VISIBLE
                                } else {
                                    binding.emptyCharactersPage.visibility = View.GONE
                                }
                                setEpisodesAdapter(it)
                                episodesAdapter.notifyDataSetChanged()
                            })
                        }
                    }
                }

                timer.start()
                return true
            }
        })
    }

    private fun getEpisodesUrl(episodesUrl: List<String>) {
        episodeViewModel.getEpisodesByCharacter(episodesUrl)
        episodeViewModel.episodesByCharacterResponse.observe(viewLifecycleOwner, {
            setEpisodesAdapter(it)
            this.episodesAdapter.notifyDataSetChanged()
        })
    }

    private fun setEpisodesAdapter(episodes: List<Episode>) {
        this.episodesAdapter =
            EpisodesAdapter(episodes, this)
        this.episodesAdapter.notifyDataSetChanged()

        binding.characterDetailEpisodes.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.characterDetailEpisodes.adapter = episodesAdapter
    }

    override fun itemClicked(id: Int) {
        if (mainViewModel.firstScreen.value == true) {
            val action =
                EpisodeListFragmentDirections.actionEpisodeListFragmentToCharacterListFragment2(
                    id
                )
            mainViewModel.firstScreen.value = false
            findNavController().navigate(action)
        } else {
            val action =
                EpisodeListFragmentDirections.actionEpisodeListFragmentToCharacterListFragment(
                    id
                )
            findNavController().navigate(action)
        }
    }


}