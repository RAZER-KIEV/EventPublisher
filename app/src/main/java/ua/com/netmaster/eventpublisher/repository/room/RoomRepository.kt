package ua.com.netmaster.eventpublisher.repository.room

import android.content.Context
import androidx.room.Room
import ua.com.netmaster.eventpublisher.model.Event

class RoomRepository(context: Context) {

    private val database: AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "db").build()

    fun insert(event: Event) {
        database.EventDao().insert(event);
    }

    fun delete(event: Event) {
        database.EventDao().delete(event);
    }

    fun getAllEvents(): List<Event> {
        return database.EventDao().getAllEvents();
    }

}