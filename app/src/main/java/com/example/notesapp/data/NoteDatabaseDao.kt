package com.example.notesapp.data

import androidx.compose.runtime.MutableState
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notesapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

    @Query("SELECT * from notesDB")
    fun getNotes():
            Flow<List<Note>>

    @Query("SELECT * from notesDB where id=:id")
    suspend fun getNoteById(id:String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("DELETE from notesDB")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note: Note)

}
