package ua.com.netmaster.eventpublisher.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.com.netmaster.eventpublisher.app.App

class PublisherMainViewModelFactory constructor(
    private val app: App,
) :
    ViewModelProvider.AndroidViewModelFactory(app) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PublisherMainViewModel::class.java)) {
            PublisherMainViewModel(app) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}