package com.example.a666.screen

import NoteScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a666.data.NotesDataSource
import com.example.a666.model.Note

class NoteViewModel : ViewModel() {
    private var noteList = mutableStateListOf<Note>()
    init{
        noteList.addAll(NotesDataSource().loadNotes())
    }
    fun addNote(note: Note) {
        noteList.add(note)
    }
    fun removeNote(note:Note){
        noteList.remove(note)
    }
    fun getAllNotes():List<Note>{
        return noteList
    }
}

