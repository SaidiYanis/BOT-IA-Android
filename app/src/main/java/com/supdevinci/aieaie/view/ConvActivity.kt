package com.supdevinci.aieaie.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

class ConversationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConversationScreen()
        }
    }

    @Composable
    fun ConversationScreen() {
        var text by remember { mutableStateOf("") }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Conversation") }
                )
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(8.dp)
                        .fillMaxSize()
                ) {
                    MessageList(modifier = Modifier.weight(1f))
                    UserInputField(text, onTextChange = { text = it }, onSend = { /* handle send */ })
                }
            }
        )
    }

    @Composable
    fun MessageList(modifier: Modifier) {
        Column(modifier = modifier) {
            // Simulated messages
            listOf(
                "Hello! How are you?",
                "I'm fine, thanks! And you?",
                "I'm doing well, thanks for asking!"
            ).forEach { message ->
                Text(
                    text = message,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                        .padding(8.dp)
                )
            }
        }
    }

    @Composable
    fun UserInputField(text: String, onTextChange: (String) -> Unit, onSend: () -> Unit) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            TextField(
                value = text,
                onValueChange = onTextChange,
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(onSend = { onSend() })
            )
            IconButton(onClick = onSend) {
                Icon(imageVector = Icons.Filled.Send, contentDescription = "Send")
            }
        }
    }
}
