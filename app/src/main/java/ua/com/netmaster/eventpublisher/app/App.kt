package ua.com.netmaster.eventpublisher.app

import android.app.Application
import android.util.Log
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ua.com.netmaster.eventpublisher.repository.Repository
import ua.com.netmaster.eventpublisher.repository.server.ServerRepository
import ua.com.netmaster.eventpublisher.repository.room.RoomRepository

class App : Application() {

    private lateinit var serverRepository: ServerRepository
    private lateinit var roomRepository: RoomRepository
    private lateinit var repository: Repository

    @DelicateCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        initRepos()
        ensureAllEventBeenSent()
    }

    @DelicateCoroutinesApi
    private fun ensureAllEventBeenSent() {
        GlobalScope.launch {
            val events = roomRepository.getAllEvents()
            if (events.isNotEmpty()) {
                events.forEach { event -> repository.postRemote(event) }
            } else {
                Log.d("Checkpoint.App", " Events DB is empty")
            }
        }
    }

    private fun initRepos() {
        serverRepository = ServerRepository()
        roomRepository = RoomRepository(this)
        repository = Repository(serverRepository, roomRepository)
    }

    fun getRepository(): Repository {
        return repository
    }
}