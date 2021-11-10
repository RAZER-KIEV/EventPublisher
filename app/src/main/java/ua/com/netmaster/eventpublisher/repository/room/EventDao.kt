package ua.com.netmaster.eventpublisher.repository.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ua.com.netmaster.eventpublisher.model.Event

@Dao
interface EventDao {

    @Insert
    fun insert(event: Event)

    @Delete
    fun delete (event: Event)

    @Query("SELECT * FROM event")
    fun getAllEvents():List<Event>
}