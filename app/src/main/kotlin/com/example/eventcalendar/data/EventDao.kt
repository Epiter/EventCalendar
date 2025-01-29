package com.example.eventcalendar.data

import androidx.room.*

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: Event)

    @Update
    suspend fun updateEvent(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)

    @Query("SELECT * FROM events WHERE id = :eventId")
    suspend fun getEventById(eventId: Int): Event?

    @Query("SELECT * FROM events ORDER BY date ASC")
    fun getAllEvents(): kotlinx.coroutines.flow.Flow<List<Event>>

    @Query("DELETE FROM events WHERE type = 'single' AND date < :currentTime")
    suspend fun deleteExpiredSingleEvents(currentTime: Long)
}
