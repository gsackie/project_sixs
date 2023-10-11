package com.example.project_six

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

//Most of my implementation is based on the task app posted in class.
@Dao
interface TaskDao {
    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)
    @Query("SELECT * FROM notes WHERE id = :key")
    fun get(key: Long): LiveData<Note>
    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAll(): LiveData<List<Note> >
}