package com.supdevinci.aieaie.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.supdevinci.aieaie.ui.theme.AIEAIETheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AIEAIETheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mes Conversations") },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Logique pour démarrer une nouvelle conversation
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Ajouter Conversation")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { padding ->
            ConversationList(modifier = Modifier.padding(padding))
        }
    )
}

@Composable
fun ConversationList(modifier: Modifier = Modifier) {
    // Supposons que vous ayez une liste de conversations
    val conversations = listOf("Conversation 1", "Conversation 2", "Conversation 3")
    LazyColumn(modifier = modifier) {
        items(conversations) { conversation ->
            ConversationItem(conversation = conversation)
        }
    }
    BottomActionButtons()
}

@Composable
fun ConversationItem(conversation: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = conversation,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun BottomActionButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = {
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Ajouter")
            Text(text = "Nouvelle Conversation")
        }

        Button(
            onClick = {
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer)
        ) {
            Icon(Icons.Filled.Delete, contentDescription = "Supprimer Tout")
            Text(text = "Supprimer Tout")
        }
    }
}
