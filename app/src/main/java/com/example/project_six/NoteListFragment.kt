import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_six.NoteListAdapter
import com.example.project_six.NoteListFragmentDirections
import com.example.project_six.NoteListViewModel
import com.example.project_six.databinding.FragmentNoteListBinding

class NoteListFragment : Fragment() {

    private lateinit var viewModel: NoteListViewModel
    private lateinit var binding: FragmentNoteListBinding
    private lateinit var adapter: NoteListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        val view = binding.root

        val recyclerView: RecyclerView = binding.notesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Initialize your adapter
        adapter = NoteListAdapter(requireFragmentManager()) { note ->
            viewModel.deleteNote(note)
        }


        // Set the adapter to the RecyclerView
        recyclerView.adapter = adapter

        binding.addNoteButton.setOnClickListener {
            val action = NoteListFragmentDirections.actionNoteListFragmentToNoteFragment()
            findNavController().navigate(action)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NoteListViewModel::class.java)

        // Observe LiveData for the list of notes and update the adapter when data changes
        viewModel.getAllNotes().observe(viewLifecycleOwner, { notes ->
            adapter.submitList(notes) // Update the list of notes
        })

    }
}
