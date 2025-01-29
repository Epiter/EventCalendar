package com.example.eventcalendar.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String?,
    val date: Long, // Timpul în milisecunde
    val type: String, // "single" sau "recurring"
    val repeatInterval: Long? = null // Intervalul recurenței (ex. zilnic, săptămânal)
)
