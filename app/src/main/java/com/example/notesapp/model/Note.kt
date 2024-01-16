package com.example.notesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID

@Entity(tableName= "notesDB")
data class Note(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "noteTitle")
    val title: String,
    @ColumnInfo(name = "noteDescription")
    val description: String,
    @ColumnInfo(name =  "noteEntryDate")
    val entryDate: Date = Date.from(Instant.now())
)