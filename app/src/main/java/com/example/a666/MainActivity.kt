package com.example.a666

import NoteScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.a666.model.Note
import com.example.a666.ui.theme._666Theme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _666Theme {
              Surface(){
                  NoteScreen(
                     notes = emptyList(),
                      onAddNote = {},
                      onRemoveNote = {}
                  )
              }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _666Theme {

    }
}