package com.example.project_six

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.project_six.databinding.FragmentNoteBinding

class NoteFragment : Fragment() {

    private val viewModel: NoteViewModel by viewModels()
    private lateinit var binding: FragmentNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val noteId = arguments?.getLong("id")

        if (noteId != null) {
            // You're in edit mode, so load the note data

            // Use the NoteRepository to fetch the note data based on the noteId
            val repository = NoteRepository(TaskDatabase.getInstance(requireContext()).taskDao)
            val note = repository.getNoteById(noteId)

            // Update the ViewModel with the retrieved note data
            viewModel.noteTitle.postValue(note.title)
            viewModel.noteDescription.postValue(note.description)
            viewModel.isEditMode.postValue(true)
        }

        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val description = binding.descriptionEditText.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                viewModel.noteTitle.value = title
                viewModel.noteDescription.value = description
                viewModel.onSaveButtonClicked()
            }
        }

        return binding.root
    }
}
