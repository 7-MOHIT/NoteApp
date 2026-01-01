import android.R.attr.contentDescription
import android.R.attr.title
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a666.R
import com.example.a666.R.string.app_name
import com.example.a666.components.NoteInputText
import com.example.a666.data.NotesDataSource
import com.example.a666.model.Note
import java.time.format.DateTimeFormatter
import kotlin.text.isNotEmpty

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }
    val scrollState = rememberScrollState()
    val context = LocalContext.current

//    val notes = remember {
//        mutableStateListOf(
//            Note(
//                title = "First Note",
//                description = "Hello JetPack compose , This is me Mohit Jangra , And i am here to learn you ,so that i can make a clear user interface for my apps ,Hello JetPack compose , This is me Mohit Jangra , And i am here to learn you ,so that i can make a clear user interface for my apps"
//            ),
//            Note(
//                title = "First Note",
//                description = "Hello JetPack compose , This is me Mohit Jangra , And i am here to learn you ,so that i can make a clear user interface for my apps "
//            ),
//            Note(
//                title = "First Note",
//                description = "Hello JetPack compose , This is me Mohit Jangra , And i am here to learn you ,so that i can make a clear user interface for my apps "
//            ),
//            Note(
//                title = "First Note",
//                description = "Hello JetPack compose , This is me Mohit Jangra , And i am here to learn you ,so that i can make a clear user interface for my apps "
//            ),
//        )
//    }
    Column(
        modifier = Modifier
            .padding(6.dp)
            .verticalScroll(scrollState)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(app_name), color = Color(0xFFEA4545)// text color - red
                )
            },
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = "Icon",
                    Modifier
                        .size(60.dp)
                        .padding(end = 20.dp)
                )
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFACACEF), //background color - yellow
            )
        )
        Divider(modifier = Modifier.height(2.dp))
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally

        ) {
            NoteInputText(
                modifier = Modifier.padding(
                    top = 9.dp,
                ), TextValue = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetterOrDigit() || char.isWhitespace()
                        }) title = it

                }
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
                modifier = Modifier
                    .padding(top = 5.dp),
                text = "Save",
                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        onAddNote(
                            Note(
                                title = title,
                                description = description
                            )
                        )
                        title = ""
                        description = ""
                        Toast.makeText(
                            context, "Note Added",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(500.dp)
            ) {
                items(notes) { note ->
                    NoteRow(
                        note = note,
                        onNoteClicked = { onRemoveNote(it) })

                }
            }

        }
    }
}


@Composable
fun NoteButton(
    modifier: Modifier, text: String, onClick: () -> Unit, enabled: Boolean = true
) {
    val infiniteTransition = rememberInfiniteTransition(label = "flow")
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2000f, // Adjust this for speed
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "offset"
    )
    val aiGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF4285F4), // Blue
            Color(0xFF9b72cb), // Purple
            Color(0xFFd96570), // Red
            Color(0xFFf4af4a), // Yellow
            Color(0xFF4285F4)  // Back to Blue
        ),
        start = Offset(offset, 0f),
        end = Offset(offset + 500f, 0f), // Width of the gradient window
        tileMode = TileMode.Repeated
    )

    Box(
        modifier = Modifier
            .border(
                width = 4.dp,
                brush = aiGradient,
                shape = CircleShape
            ), contentAlignment = Alignment.Center
    ) {
//        content()
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                onClick = onClick,
                shape = CircleShape,
                enabled = enabled,
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB194E7), // Background color
                    contentColor = Color.Blue          // Text/Icon color
                )
            ) {
                Text(text)
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
    val rainbowGradient = Brush.linearGradient(
        colors = listOf(Color.Red, Color.Magenta, Color.Blue)
    )
    Surface(
        modifier = Modifier
            .padding(7.dp)
            .heightIn(max = 150.dp)
            .fillMaxWidth(),
        border = BorderStroke(2.dp, rainbowGradient),
        color = Color(0xFFFFC1CC),
        shadowElevation = 6.dp,
        shape = RoundedCornerShape(30.dp)
    )
    {
        Column(
            modifier
                .clickable {
                    onNoteClicked(note)
                }
                .padding(
                    horizontal = 10.dp,
                    vertical = 6.dp
                ),
            horizontalAlignment = Alignment.Start) {
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
