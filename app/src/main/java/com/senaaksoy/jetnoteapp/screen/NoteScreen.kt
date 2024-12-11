@file:OptIn(ExperimentalMaterial3Api::class)

package com.senaaksoy.jetnoteapp.screen

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.senaaksoy.jetnoteapp.R
import com.senaaksoy.jetnoteapp.components.EditTextField
import com.senaaksoy.jetnoteapp.components.NoteButton
import com.senaaksoy.jetnoteapp.components.NoteTopAppBar
import com.senaaksoy.jetnoteapp.viewModel.NoteUiState
import com.senaaksoy.jetnoteapp.viewModel.NoteViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun NoteScreen(viewModel: NoteViewModel= viewModel()){

    val keyboardController = LocalSoftwareKeyboardController.current


    Column {
    NoteTopAppBar()
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        EditTextField(value =viewModel.titleInput ,
            onValueChange ={viewModel.upDateTitleInput(it)} ,
            modifier = Modifier.padding(4.dp),
            label = R.string.title ,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            colors =TextFieldDefaults.colors(unfocusedContainerColor = Color.White))
        EditTextField(value =viewModel.descriptionInput ,
            onValueChange ={viewModel.upDateDescriptionInput(it)} ,
            modifier = Modifier.padding(4.dp),
            label = R.string.description ,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            colors =TextFieldDefaults.colors(unfocusedContainerColor = Color.White))

     NoteButton(
         onClick = {
        viewModel.addNote(NoteUiState(title = viewModel.titleInput, description = viewModel.descriptionInput))
         keyboardController?.hide()
                   },
         text = R.string.save,
         enabled = (viewModel.titleInput.isNotBlank()&&viewModel.descriptionInput.isNotBlank())
     )
        EditNoteCard(viewModel.notesList)
    } } }

@Composable
fun EditNoteCard(notesList:List<NoteUiState>,viewModel: NoteViewModel= viewModel()){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(viewModel.notesList){ note->

            Card(
                modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { viewModel.removeNote(note) },
                colors =CardDefaults.cardColors( Color.LightGray))
            {
                Column(
                    modifier=Modifier.padding(8.dp)) {
                    Text(
                        text = viewModel.getStringFromResource(value =note.title ),
                        fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text=viewModel.getStringFromResource(value = note.description))

                }
            }
        }
    }
}