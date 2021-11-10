package ua.com.netmaster.eventpublisher.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Event(val subject: String, val payload: String, @PrimaryKey val timeStamp: Long) {
    companion object {
        val PAYLOAD_KEY = "PAYLOAD_KEY"
        val SUBJECT_KEY = "SUBJECT_KEY"
        val TIMESTAMP_KEY = "TIMESTAMP_KEY"
    }
}
