package com.example.runescroll.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.example.runescroll.viewmodel.UserStats

@Dao
interface QuestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuest(quest: QuestEntity)

    @Update
    suspend fun updateQuest(quest: QuestEntity)

    @Query("UPDATE quests SET status = :status WHERE id = :questId")
    suspend fun updateQuestStatus(questId: Int, status: String)

    @Delete
    suspend fun deleteQuest(quest: QuestEntity)

    @Query("SELECT * FROM quests WHERE status = 'ACTIVE'")
    fun getAllActiveQuests(): Flow<List<QuestEntity>>

    @Query("SELECT * FROM quests WHERE type = :questType")
    fun getQuestsByType(questType: QuestType): Flow<List<QuestEntity>>

    @Query("SELECT * FROM quests WHERE id = :questId LIMIT 1")
    fun getQuestById(questId: Int): Flow<QuestEntity?>

    @Query("DELETE FROM quests WHERE status = :status")
    suspend fun deleteQuestsByStatus(status: QuestStatus)

    // ✅ Fix: Import corect pentru `UserStats`
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserStats(userStats: com.example.runescroll.viewmodel.UserStats)

    @Query("SELECT * FROM user_stats LIMIT 1")
    fun getUserStats(): Flow<com.example.runescroll.viewmodel.UserStats>

    @Query("UPDATE user_stats SET level = :level, xp = :xp WHERE id = 1")
    suspend fun updateUserStats(level: Int, xp: Int)

    @Query("DELETE FROM quests WHERE id = :questId")
    suspend fun deleteQuestById(questId: Int)
}
