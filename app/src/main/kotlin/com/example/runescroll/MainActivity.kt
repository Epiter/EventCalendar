package com.example.runescroll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.runescroll.data.QuestDatabase
import com.example.runescroll.repository.QuestRepository
import com.example.runescroll.ui.QuestScreen
import com.example.runescroll.viewmodel.QuestViewModel
import com.example.runescroll.viewmodel.QuestViewModelFactory
import com.example.runescroll.ui.theme.RuneScrollTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = QuestDatabase.getDatabase(this)
        val repository = QuestRepository(database.questDao())

        val viewModelFactory = QuestViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[QuestViewModel::class.java]

        setContent {
            RuneScrollTheme {
                QuestScreen(viewModel = viewModel)
            }
        }
    }
}
