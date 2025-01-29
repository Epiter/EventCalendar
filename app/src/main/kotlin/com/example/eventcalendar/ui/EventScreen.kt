package com.example.eventcalendar.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.eventcalendar.data.*

@Composable
fun EventScreen(viewModel: EventViewModel = viewModel()) {
    val context = LocalContext.current
    val events by viewModel.allEvents.collectAsState(initial = emptyList())

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Event Title") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Event Description") },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )

        Button(
            onClick = {
                if (title.isNotBlank()) {
                    val event = Event(
                        title = title,
                        description = description,
                        date = System.currentTimeMillis(),
                        type = "single"
                    )
                    viewModel.insert(event)
                    title = ""
                    description = ""
                    Toast.makeText(context, "Event added!", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        ) {
            Text("Add Event")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(events) { event ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = event.title, style = MaterialTheme.typography.headlineSmall)
                        Text(text = event.description ?: "No description")

                        Row {
                            Button(onClick = { viewModel.delete(event) }) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        }
    }
}
