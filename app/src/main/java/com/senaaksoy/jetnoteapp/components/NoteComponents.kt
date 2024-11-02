package com.senaaksoy.jetnoteapp.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.senaaksoy.jetnoteapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteTopAppBar(modifier: Modifier=Modifier){
    Surface(modifier = modifier.fillMaxWidth()) {
        TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) },
            actions ={Icon(imageVector = Icons.Filled.Notifications , contentDescription = null)},
            colors = TopAppBarDefaults.topAppBarColors(Color.Gray))
    }

}

@Composable
fun EditTextField( modifier: Modifier=Modifier,
    value:String,
    onValueChange:(String)->Unit,
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    maxLine:Int=1,
    colors:TextFieldColors

){
    TextField(value =value ,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(label))},
        maxLines = maxLine,
        keyboardOptions = keyboardOptions,
        colors = colors,
        modifier = modifier)
}

@Composable
fun NoteButton(
    onClick:()->Unit,
    @StringRes text :Int,
    enabled:Boolean
){
    Button(onClick = onClick,
        enabled=enabled) {
        Text(text = stringResource(text))
    }

}