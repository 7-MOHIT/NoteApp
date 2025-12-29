import android.R.attr.contentDescription
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen() {
    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.padding(6.dp)
    ) {

        //top bar showing name of the app and a notification icon
        TopAppBar(
            title = {
                Text(
                    text = stringResource(app_name),
                    color = Color(0xFFEA4545)// text color - red
                )
            }, actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications, contentDescription = "Icon"
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFEFDC2D), //background color - yellow
            )
        )

        //creating text fields and a save button
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            NoteInputText(
                modifier = Modifier.padding(
                    top = 9.dp,
                    bottom = 8.dp
                ),
                TextValue = title,
                label = "Title",
                maxLines = 1,
                onTextChange = {},
                onImeAction = {}

            )
            NoteInputText(
                modifier = Modifier.padding(
                    top = 9.dp,
                    bottom = 8.dp
                ),
                TextValue = description,
                label = "Add a Note",
                maxLines = 1,
                onTextChange = {},
                onImeAction = {}
            )
            NoteButton(
                modifier = Modifier,
                text = "Save",
               onClick ={}
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun NotesScreeenPreview() {
    NoteScreen()
}

@Composable
fun NoteButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(onClick = onClick,
        shape = CircleShape,
        enabled = enabled,
        modifier = Modifier
    ) {
        Text(text,
            )
    }
}