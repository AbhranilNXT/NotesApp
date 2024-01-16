package com.example.notesapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesapp.R
import com.example.notesapp.components.NoteButton
import com.example.notesapp.components.NoteInputText
import com.example.notesapp.components.NoteRow
import com.example.notesapp.model.Note

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

    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = {
                          Text(text = stringResource(id = R.string.app_name))
        },
            actions = {
                Icon(imageVector = Icons.Default.Star, contentDescription = "TopBarIcon")
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color(0xff2a9d8f)))


        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            NoteInputText(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                ,text = title,
                label = "Title",
                onTextChange = {
                    if(it.all {
                        char -> char.isLetterOrDigit() || char.isWhitespace()
                        }) title = it
                })

            NoteInputText(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                ,text = description,
                label = "Add a Note",
                onTextChange = {
                    if(it.all {
                        char -> char.isLetterOrDigit() || char.isWhitespace()
                        }) description = it
                })

            NoteButton(text = "Save",
                onClick = {
                if(title.isNotEmpty() && description.isNotEmpty()) {
                    onAddNote(Note(title = title, description = description))
                    title=""
                    description=""
                    Toast.makeText(context, "Note Added!",Toast.LENGTH_SHORT).show()
                }
                    else Toast.makeText(context,"Input Fields cannot be empty !?",Toast.LENGTH_SHORT).show()
            })

        }
        Divider(modifier = Modifier.padding(8.dp))
        LazyColumn {
            items(notes){ note ->
                NoteRow(note = note, onNoteClicked = {
                    onRemoveNote(note)
                    Toast.makeText(context, "Note Deleted !!",Toast.LENGTH_SHORT).show()
                },)
            }
        }
    }

}

