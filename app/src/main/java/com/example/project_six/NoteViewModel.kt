package com.example.project_six

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NoteViewModel : ViewModel() {
    val noteTitle = MutableLiveData<String>()
    val noteDescription = MutableLiveData<String>()

    // Inject the repository into the ViewModel
    private val repository = NoteRepository(TaskDatabase.getInstance().taskDao)

    fun onSaveButtonClicked() {
        // Get the text from the LiveData
        val title = noteTitle.value
        val description = noteDescription.value

        if (title != null && description != null) {
            // Create or update a note
            val note = Note(title = title, description = description)

            // Call the repository to save or update the note in the database
            viewModelScope.launch {
                repository.update(note)
                navigateBackToMainActivity()
            }
        }
    }

    fun deleteNote() {
        // Call the repository to delete the note from the database
        viewModelScope.launch {
            repository.delete(Note(
                title = noteTitle.value!!,
                description = noteDescription.value!!
            ))
            navigateBackToMainActivity()
        }
    }

    private fun navigateBackToMainActivity() {
        // Navigate back to the MainActivity
    }
}
