package com.example.project_six

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_six.Note
import com.example.project_six.NoteRepository
import kotlinx.coroutines.launch

class NoteListViewModel(private val repository: NoteRepository) : ViewModel() {
    // LiveData to hold the list of notes
    private var allNotes: LiveData<List<Note>> = repository.getAllNotes()

    // Function to get all notes
    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }
    // Function to delete a note
    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.delete(note)
        }
    }
}
