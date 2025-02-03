package com.example.runescroll.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.runescroll.viewmodel.QuestViewModel
import com.example.runescroll.viewmodel.UserStats
import com.example.runescroll.data.QuestEntity


@Composable
fun QuestScreen(viewModel: QuestViewModel = viewModel()) {
    val activeQuests by viewModel.activeQuests.collectAsState(initial = emptyList())
    val userStatsState = viewModel.userStats.collectAsState(initial = UserStats()).value



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "User Level: ${userStatsState?.level ?: 1}, XP: ${userStatsState?.xp ?: 0}",
            style = MaterialTheme.typography.headlineSmall
        )



        Spacer(modifier = Modifier.height(16.dp))

        activeQuests.forEach { quest ->
            QuestItem(quest)
        }
    }
}

@Composable
fun QuestItem(quest: QuestEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = quest.title, style = MaterialTheme.typography.headlineSmall)
            Text(text = quest.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = "XP Reward: ${quest.xpReward}", style = MaterialTheme.typography.labelMedium)
        }
    }
}
