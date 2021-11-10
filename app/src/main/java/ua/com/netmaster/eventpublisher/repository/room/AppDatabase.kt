package ua.com.netmaster.eventpublisher.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.com.netmaster.eventpublisher.model.Event

@Database(entities = [Event::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun EventDao(): EventDao
}