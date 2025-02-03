package com.example.runescroll.data

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.runescroll.viewmodel.UserStats


@Database(entities = [QuestEntity::class, UserStats::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class QuestDatabase : RoomDatabase() {
    abstract fun questDao(): QuestDao


    companion object {
        @Volatile
        private var INSTANCE: QuestDatabase? = null

        fun getDatabase(context: Context): QuestDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuestDatabase::class.java,
                    "quest_database"
                )
                    .addTypeConverter(Converters())
                    .addCallback(DatabaseCallback())
                    .fallbackToDestructiveMigration() // Resetează baza de date dacă schema se schimbă
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DatabaseCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            CoroutineScope(Dispatchers.IO).launch {
                INSTANCE?.questDao()?.insertUserStats(UserStats()) // Inițializează UserStats
            }
        }
    }
}
