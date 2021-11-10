package ua.com.netmaster.eventpublisher.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.*
import ua.com.netmaster.eventpublisher.app.App
import ua.com.netmaster.eventpublisher.model.Event
import ua.com.netmaster.eventpublisher.model.Event.Companion.PAYLOAD_KEY
import ua.com.netmaster.eventpublisher.model.Event.Companion.SUBJECT_KEY
import ua.com.netmaster.eventpublisher.model.Event.Companion.TIMESTAMP_KEY

class EventPosterWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    @DelicateCoroutinesApi
    override suspend fun doWork(): Result {
        Log.d("Checkpoint.EventPosterWorker", "doWork")
        return try {
            withContext(Dispatchers.IO) {
                val subject = inputData.getString(SUBJECT_KEY)
                val payload = inputData.getString(PAYLOAD_KEY)
                val timeStamp = inputData.getLong(TIMESTAMP_KEY, 0)
                Log.d("Checkpoint.EventPosterWorker", "doWork. timeStamp = $timeStamp")
                val repo = (applicationContext as App).getRepository()
                repo.postEvent(
                    Event(
                        requireNotNull(subject),
                        requireNotNull(payload),
                        timeStamp
                    )
                )
                return@withContext Result.success()
            }
        } catch (ex: Exception) {
            Result.failure()
        }
    }
}