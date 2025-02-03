package com.example.runescroll.data

import androidx.room.*
import kotlinx.serialization.Serializable

@Entity(tableName = "quests")
@Serializable
data class QuestEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val xpReward: Int,
    val status: QuestStatus,
    val type: QuestType,
    val recurrenceRule: String,
    @TypeConverters(Converters::class) val objectives: List<String>
)

// Enum pentru statusul quest-ului
enum class QuestStatus {
    IN_PROGRESS,
    COMPLETED,
    FAILED,
    PARTIAL
}

// Enum pentru tipurile de quest-uri
enum class QuestType {
    UNIQUE,
    DAILY,
    EPIC
}
