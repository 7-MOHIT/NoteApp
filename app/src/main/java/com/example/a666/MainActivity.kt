package com.example.a666

import NoteScreen
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.a666.data.NotesDataSource
import com.example.a666.model.Note
import com.example.a666.ui.theme._666Theme


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _666Theme {
                Surface() {
                    val notes = remember {
                        mutableStateListOf<Note>()
                    }
                    NoteScreen(
                        notes = notes,
//                        notes = NotesDataSource().loadNotes(),
                        onAddNote = {note->
                            notes.add(0,note)
                        },
                        onRemoveNote = { note->
                            // for deleting it in a single tap on it
//                           notes.remove(note)
                        }
                    )
                }
            }
        }
    }
}



//
//@RequiresApi(Build.VERSION_CODES.O)
//@ExperimentalComposeUiApi
//@Composable
//fun NotesApp(noteViewModel: NoteViewModel) {
//    val notesList = noteViewModel.noteList.collectAsState().value
//
//    NoteScreen(notes = notesList,
//        onRemoveNote = { noteViewModel.removeNote(it) },
//        onAddNote = { noteViewModel.addNote(it) })
//
//}