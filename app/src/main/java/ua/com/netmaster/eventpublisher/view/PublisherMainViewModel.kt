package ua.com.netmaster.eventpublisher.view

import androidx.lifecycle.AndroidViewModel
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import ua.com.netmaster.eventpublisher.app.App
import ua.com.netmaster.eventpublisher.model.Event
import ua.com.netmaster.eventpublisher.model.Event.Companion.PAYLOAD_KEY
import ua.com.netmaster.eventpublisher.model.Event.Companion.SUBJECT_KEY
import ua.com.netmaster.eventpublisher.model.Event.Companion.TIMESTAMP_KEY
import ua.com.netmaster.eventpublisher.worker.EventPosterWorker

class PublisherMainViewModel(app: App) : AndroidViewModel(app) {

    private val workManager = WorkManager.getInstance(app)

    fun postEvent(event: Event) {
        val postRequest = OneTimeWorkRequestBuilder<EventPosterWorker>()
            .setInputData(createInputDataForEvent(event))
            .build()
        workManager.enqueue(postRequest)
    }

    private fun createInputDataForEvent(event: Event): Data {
        val builder = Data.Builder()
        event.let {
            builder.putString(PAYLOAD_KEY, event.payload)
            builder.putString(SUBJECT_KEY, event.subject)
            builder.putLong(TIMESTAMP_KEY, event.timeStamp)
        }
        return builder.build()
    }
}