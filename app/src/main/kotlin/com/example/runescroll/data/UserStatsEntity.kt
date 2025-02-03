package com.example.runescroll.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_stats")
data class UserStatsEntity(
    @PrimaryKey val id: Int = 1, // Un singur utilizator
    val level: Int = 1,
    val xp: Int = 0
)
