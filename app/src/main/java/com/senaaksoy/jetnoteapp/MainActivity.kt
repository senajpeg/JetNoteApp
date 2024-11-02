package com.senaaksoy.jetnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.senaaksoy.jetnoteapp.screen.NoteScreen
import com.senaaksoy.jetnoteapp.ui.theme.JetNoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetNoteAppTheme {
             NoteScreen()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun JetNoteAppPreview() {
    JetNoteAppTheme {
         NoteScreen()
    }
}