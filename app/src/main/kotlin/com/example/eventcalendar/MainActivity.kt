package com.example.eventcalendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.eventcalendar.data.*

import com.example.eventcalendar.ui.EventScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inițializează baza de date și repository-ul
        val database = EventDatabase.getDatabase(this)
        val repository = EventRepository(database.eventDao())

        // Creeaza ViewModel folosind factory
        val viewModelFactory = EventViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(EventViewModel::class.java)

        setContent {
            EventScreen(viewModel = viewModel)
        }
    }
}
