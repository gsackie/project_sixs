package com.example.project_six

import androidx.lifecycle.LiveData

class NoteRepository(private val taskDao: TaskDao) {

    // Function to insert a note into the database
    suspend fun insert(note: Note) {
        taskDao.insert(note)
    }

    // Function to update a note in the database
    suspend fun update(note: Note) {
        taskDao.update(note)
    }

    // Function to delete a note from the database
    suspend fun delete(note: Note) {
        taskDao.delete(note)
    }

    // Function to get a note by its ID
    fun getNoteById(id: Long): LiveData<Note> {
        return taskDao.get(id)
    }

    // Function to get all notes from the database
    fun getAllNotes(): LiveData<List<Note>> {
        return taskDao.getAll()
    }
}
