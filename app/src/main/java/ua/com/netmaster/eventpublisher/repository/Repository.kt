package ua.com.netmaster.eventpublisher.repository

import android.util.Log
import ua.com.netmaster.eventpublisher.model.Event
import ua.com.netmaster.eventpublisher.repository.room.RoomRepository
import ua.com.netmaster.eventpublisher.repository.server.ServerRepository

class Repository(
    private val serverRepository: ServerRepository,
    private val roomRepository: RoomRepository
) {
    suspend fun postEvent(event: Event) {
        Log.d("Checkpoint.App", " postEvent $event")
        roomRepository.insert(event)
        postRemote(event)
    }

    suspend fun postRemote(event: Event) {
        val response = serverRepository.post(event)
        if (response.isSuccessful) {
            roomRepository.delete(event)
        } else {
            //todo : retry?
            Log.e("Repository", "post to server is failed")
        }
    }

}