package ge.sweeft.rickandmortyalbum.episode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.sweeft.rickandmortyalbum.databinding.EpisodeItemBinding
import ge.sweeft.rickandmortyalbum.dataclass.Episode

class EpisodesAdapter(
    private var episodes: List<Episode>,
    private val listener: CustomClickListener
) : RecyclerView.Adapter<EpisodesAdapter.ListSelectionViewHolder>() {

    interface CustomClickListener {
        fun itemClicked(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val binding = EpisodeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListSelectionViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.onBind(episodes[position])
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    class ListSelectionViewHolder(
        private val binding: EpisodeItemBinding,
        private val listener: CustomClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(episode: Episode) {
            binding.episodeName.text = episode.name
            binding.episodeDate.text = episode.air_date
            binding.episodeNumber.text = episode.episode

            binding.root.setOnClickListener {
                listener.itemClicked(episode.id)
            }
        }
    }

}