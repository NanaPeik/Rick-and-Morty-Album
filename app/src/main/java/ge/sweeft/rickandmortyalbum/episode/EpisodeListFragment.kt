package ge.sweeft.rickandmortyalbum.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ge.sweeft.rickandmortyalbum.databinding.FragmentEpisodeListBinding
import ge.sweeft.rickandmortyalbum.dataclass.Episode
import ge.sweeft.rickandmortyalbum.viewmodel.EpisodeViewModel

@AndroidEntryPoint
class EpisodeListFragment : Fragment() {

    private val episodeViewModel: EpisodeViewModel by viewModels()
    private lateinit var binding: FragmentEpisodeListBinding
    private lateinit var episodesAdapter: EpisodesAdapter

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
        episodeViewModel.episode.value = ""
        episodeViewModel.response.observe(viewLifecycleOwner, {
            if (it != null) {
                setEpisodesAdapter(it.results)
            }
        })
    }

    private fun setEpisodesAdapter(episodes: List<Episode>) {
        this.episodesAdapter = EpisodesAdapter(episodes)

        binding.episodeRecycler.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.episodeRecycler.adapter = episodesAdapter
    }
}