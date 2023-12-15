package com.example.project_six

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import kotlinx.coroutines.launch

class NoteViewModel : ViewModel() {
    val noteTitle = MutableLiveData<String>()
    val noteDescription = MutableLiveData<String>()
    val isEditMode = MutableLiveData<Boolean>()

    // Inject the repository into the ViewModel
    private val repository = NoteRepository(TaskDatabase.getInstance().taskDao)

    fun onSaveButtonClicked() {
        // Get the text from the LiveData
        val title = noteTitle.value
        val description = noteDescription.value

        if (title != null && description != null) {
            // Create or update a note
            val note = if (isEditMode.value == true) {
                Note(title = title, description = description, id = 0) // Assuming 0 for the new note
            } else {
                Note(title = title, description = description)
            }

            // Call the repository to save or update the note in the database
            viewModelScope.launch {
                if (isEditMode.value == true) {
                    repository.update(note)
                } else {
                    repository.insert(note)
                }
                navigateBackToMainActivity()
            }
        }
    }

    fun deleteNote() {
        // Call the repository to delete the note from the database
        viewModelScope.launch {
            val title = noteTitle.value ?: ""
            val description = noteDescription.value ?: ""
            repository.delete(Note(title = title, description = description))
            navigateBackToMainActivity()
        }
    }

    private fun navigateBackToMainActivity() {
        viewModelScope.launch {
            findNavController().popBackStack()
        }
    }
}
