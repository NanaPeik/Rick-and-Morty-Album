package ge.sweeft.rickandmortyalbum.episode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ge.sweeft.rickandmortyalbum.databinding.EpisodeItemBinding
import ge.sweeft.rickandmortyalbum.dataclass.Episode

class EpisodesAdapter(private var episodes: List<Episode>, fragmentManager: FragmentManager) :
    RecyclerView.Adapter<EpisodesAdapter.ListSelectionViewHolder>() {

    private var episodesList: ArrayList<Episode> = episodes as ArrayList<Episode>
    private var fragmentManager:FragmentManager = fragmentManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val binding = EpisodeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListSelectionViewHolder(binding,fragmentManager)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.onBind(episodesList[position])
    }

    override fun getItemCount(): Int {
        return episodesList.size
    }

    class ListSelectionViewHolder(
        private val binding: EpisodeItemBinding,
        private val fragmentManager: FragmentManager
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(episode: Episode) {
            binding.episodeName.text = episode.name
            binding.episodeDate.text = episode.air_date
            binding.episodeNumber.text = episode.episode

            binding.root.setOnClickListener {
                val action =
                    EpisodeListFragmentDirections.actionEpisodeListFragmentToCharacterListFragment(
                        episode.id
                    )
                fragmentManager.popBackStack()
                it.findNavController().navigate(action)
            }
        }
    }

    fun search(newText: String) {
        episodesList= arrayListOf()
        for(episode in episodes){
            if(episode.name.contains(newText)){
                episodesList.add(episode)
            }
        }
        notifyDataSetChanged()
    }
}