package ge.sweeft.rickandmortyalbum.character

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ge.sweeft.rickandmortyalbum.R
import ge.sweeft.rickandmortyalbum.databinding.CharacterItemBinding
import ge.sweeft.rickandmortyalbum.dataclass.Character
import ge.sweeft.rickandmortyalbum.episode.EpisodeListFragment

class CharacterAdapter(
    private var characters: List<Character>,
    fragmentManager: FragmentManager
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private var charactersList: ArrayList<Character> = characters as ArrayList<Character>
    private var fragmentManager: FragmentManager = fragmentManager

    class ViewHolder(var binding: CharacterItemBinding, var fragmentManager: FragmentManager) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(character: Character) {
            binding.characterName.text = character.name
            binding.characterStatus.text = character.status
            Picasso.get().load(character.image).into(binding.characterImage)

            binding.root.setOnClickListener {
                val action =
                    CharacterListFragmentDirections.actionCharacterListFragmentToEpisodeListFragment(
                        character.id
                    )

                it.findNavController().navigate(action)
                fragmentManager.popBackStack()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding =
            CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, fragmentManager)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(charactersList[position])
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    fun search(newText: String?) {
        charactersList = arrayListOf()
        for (character in characters) {
            if (newText?.let { character.name.contains(it) } == true) {
                charactersList.add(character)
            }
        }
        notifyDataSetChanged()
    }
}