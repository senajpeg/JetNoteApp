@file:OptIn(ExperimentalMaterial3Api::class)

package com.senaaksoy.jetnoteapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.senaaksoy.jetnoteapp.R
import com.senaaksoy.jetnoteapp.components.EditTextField
import com.senaaksoy.jetnoteapp.components.NoteButton
import com.senaaksoy.jetnoteapp.components.NoteTopAppBar



data class Note(
  val title: Any,
  val description: Any
)

@Composable
fun NoteScreen(){
    val listA = listOf(
        Note(R.string.title_1,  R.string.note_1),
        Note( R.string.title_2,  R.string.note_2)
    )

    var titleInput by remember { mutableStateOf("") }
    var descriptionInput by remember { mutableStateOf("") }

    var notesList by remember { mutableStateOf(listA) }

    Column {
    NoteTopAppBar()
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        EditTextField(value =titleInput ,
            onValueChange ={titleInput=it} ,
            modifier = Modifier.padding(4.dp),
            label = R.string.title ,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            colors =TextFieldDefaults.colors(unfocusedContainerColor = Color.White))
        EditTextField(value =descriptionInput ,
            onValueChange ={descriptionInput=it} ,
            modifier = Modifier.padding(4.dp),
            label = R.string.description ,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            colors =TextFieldDefaults.colors(unfocusedContainerColor = Color.White))
     NoteButton(onClick = {
         if(titleInput.isNotBlank() && descriptionInput.isNotBlank()){
       notesList=notesList + Note(title = titleInput, description = descriptionInput)
             titleInput = ""
             descriptionInput = ""
         }
     },
         text = R.string.save,
         enabled = (titleInput.isNotBlank()&&descriptionInput.isNotBlank())
     )
        EditNoteCard(notesList)

    }
}
}

@Composable
fun EditNoteCard(notesList:List<Note>){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(notesList){ note->
            Card(  modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                colors =CardDefaults.cardColors( Color.LightGray))
            {
                Column(modifier=Modifier.padding(8.dp)) {
                    Text(text = if (note.title is Int) stringResource(note.title) else note.title as String,
                        fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text=if (note.description is Int) stringResource(note.description) else note.description as String)

                }
            }
        }
    }











}