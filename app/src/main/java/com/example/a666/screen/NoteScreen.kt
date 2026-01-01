import android.R.attr.contentDescription
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a666.R
import com.example.a666.R.string.app_name
import com.example.a666.components.NoteInputText
import com.example.a666.data.NotesDataSource
import com.example.a666.model.Note
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
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
    val scrollState = rememberScrollState()

    val notes = remember {
        mutableStateListOf(
            Note(
                title = "First Note",
                description = "Hello JetPack compose , This is me Mohit Jangra , And i am here to learn you ,so that i can make a clear user interface for my apps ,Hello JetPack compose , This is me Mohit Jangra , And i am here to learn you ,so that i can make a clear user interface for my apps"
            ),
            Note(
                title = "First Note",
                description = "Hello JetPack compose , This is me Mohit Jangra , And i am here to learn you ,so that i can make a clear user interface for my apps "
            ),
            Note(
                title = "First Note",
                description = "Hello JetPack compose , This is me Mohit Jangra , And i am here to learn you ,so that i can make a clear user interface for my apps "
            ),
            Note(
                title = "First Note",
                description = "Hello JetPack compose , This is me Mohit Jangra , And i am here to learn you ,so that i can make a clear user interface for my apps "
            ),
        )
    }
    Column(
        modifier = Modifier
            .padding(6.dp)
            .verticalScroll(scrollState)
    ) {
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
            LazyColumn(
                modifier = Modifier.height(500.dp)
            ) {
                items(notes) { note ->
                    NoteRow(
                        note = note,
                        onNoteClicked = {})


                }
            }

        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .heightIn(max = 150.dp)
            .clip(RoundedCornerShape(30.dp))
            .fillMaxWidth(0.8f),
        color = Color(0xFF91D3EC),
        shadowElevation = 6.dp
    )
    {
        Column(
            modifier
                .clickable {}
                .padding(
                    horizontal = 14.dp,
                    vertical = 6.dp
                ),
            horizontalAlignment = Alignment.Start)
        {
            Text(text = note.title)
            Text(text = note.description)
            Text(
                text = note.entryDate.format(
                    DateTimeFormatter.ofPattern("EEE, d MMM")
                )
            )
        }
    }
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