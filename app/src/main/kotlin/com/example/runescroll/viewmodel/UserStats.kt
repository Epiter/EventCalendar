package com.example.runescroll.viewmodel

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_stats")
data class UserStats(
    @PrimaryKey val id: Int = 1,
    val level: Int = 1,
    val xp: Int = 0
) {
    fun getNextLevelXp(): Int {
        return (level * level) * 100 // Formula pentru următorul nivel
    }

    fun addXp(amount: Int): UserStats {
        val newXp = xp + amount
        val nextLevelXp = getNextLevelXp()

        return if (newXp >= nextLevelXp) {
            copy(level = level + 1, xp = newXp - nextLevelXp)
        } else {
            copy(xp = newXp)
        }
    }
}
