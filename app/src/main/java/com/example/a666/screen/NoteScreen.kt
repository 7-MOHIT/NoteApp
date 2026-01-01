import android.R.attr.contentDescription
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a666.R
import com.example.a666.R.string.app_name
import com.example.a666.components.NoteInputText
import com.example.a666.data.NotesDataSource
import com.example.a666.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>, onAddNote: (Note) -> Unit, onRemoveNote: (Note) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

//    val notes = remember {
//        mutableStateListOf(
//            Note(title = "First Note", description = "Hello Mohit!"),
//            Note(title = "Second Note", description = "This is a test")
//        )
//    }


    Column(
        modifier = Modifier.padding(6.dp)
    ) {

        LazyColumn(
            modifier = Modifier.height(200.dp)
        ) {
            items(notes) { note ->
                Text(
                    text = note.title, modifier = Modifier.padding(8.dp)
                )
            }
        }
        //top bar showing name of the app and a notification icon
        TopAppBar(
            title = {
            Text(
                text = stringResource(app_name), color = Color(0xFFEA4545)// text color - red
            )
        }, actions = {
            Icon(
                imageVector = Icons.Rounded.Notifications, contentDescription = "Icon"
            )
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFEFDC2D), //background color - yellow
        )
        )
        Divider(modifier = Modifier.height(2.dp))
        //creating text fields and a save button
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally

        ) {
            NoteInputText(
                modifier = Modifier.padding(
                top = 9.dp,
            ), TextValue = title, label = "Title", maxLines = 1, onTextChange = {
                if (it.all { char ->
                        char.isLetterOrDigit() || char.isWhitespace()
                    }) title = it

            }, onImeAction = {}

            )
            Divider(
                modifier = Modifier
                    .height(2.dp)
                    .padding(start = 50.dp)
            )
            NoteInputText(
                modifier = Modifier.padding(
                top = 9.dp,
            ), TextValue = description, label = "Add a Note", maxLines = 1, onTextChange = {
                if (it.all { char ->
                        char.isWhitespace() || char.isLetterOrDigit()
                    }) description = it

            }, onImeAction = {})
            Divider(
                modifier = Modifier
                    .height(2.dp)
                    .padding(start = 50.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            NoteButton(
                modifier = Modifier.padding(top = 5.dp), text = "Save", onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        // save or add to list
                        title = ""
                        description = ""
                    }
                })
            Divider(modifier = Modifier.padding(10.dp))

            Text(text = "Mohit jangra")

            Text(text = "Mohit jangra")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesScreeenPreview() {
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}


@Composable
fun NoteButton(
    modifier: Modifier, text: String, onClick: () -> Unit, enabled: Boolean = true
) {
    Button(
        onClick = onClick, shape = CircleShape, enabled = enabled, modifier = Modifier
    ) {
        Text(text)
    }
}