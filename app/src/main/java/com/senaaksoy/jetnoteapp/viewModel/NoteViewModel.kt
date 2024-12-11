package com.senaaksoy.jetnoteapp.viewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.senaaksoy.jetnoteapp.R


class NoteViewModel : ViewModel() {
    var titleInput by mutableStateOf("")
        private set
    var descriptionInput by mutableStateOf("")
        private set

    var notesList = mutableStateListOf<NoteUiState>()

    init {
        val listA = listOf(
            NoteUiState(R.string.title_1, R.string.note_1),
            NoteUiState(R.string.title_2, R.string.note_2)
        )
        notesList.addAll(listA)
    }

    fun upDateTitleInput(newTitleInput:String) {
    titleInput=newTitleInput

    }
    fun upDateDescriptionInput(newDescriptionInput:String){
        descriptionInput=newDescriptionInput
    }

    fun addNote(note: NoteUiState){
        if(titleInput.isNotBlank() && descriptionInput.isNotBlank()){
            notesList.add(note)

            titleInput = ""
            descriptionInput = ""
        }
    }
    fun removeNote(note:NoteUiState){
        notesList.remove(note)
    }
@Composable
    fun getStringFromResource(value: Any): String {
        return if (value is Int) {
            stringResource(value)
        } else {
            value as String
        }
    }
}