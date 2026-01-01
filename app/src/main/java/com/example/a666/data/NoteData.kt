package com.example.a666.data

import com.example.a666.model.Note

class NotesDataSource {
    fun loadNotes(): List<Note> {
        return listOf(
            Note(
                title = "A good day",
                description = "We went on a vacation by the land"
            ),
            Note(
                title = "Exercise",
                description = "Here is the description"
            )
        )
    }
}