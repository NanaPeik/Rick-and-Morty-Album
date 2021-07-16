package ge.sweeft.rickandmortyalbum.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ge.sweeft.rickandmortyalbum.databinding.CharacterItemBinding
import ge.sweeft.rickandmortyalbum.dataclass.Character

class CharacterAdapter(
    private var characters: List<Character>
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    class ViewHolder(var binding: CharacterItemBinding) :
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
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding =
            CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(characters[position])
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}