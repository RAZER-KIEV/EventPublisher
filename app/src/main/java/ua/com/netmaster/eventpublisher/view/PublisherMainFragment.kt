package ua.com.netmaster.eventpublisher.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.*
import ua.com.netmaster.eventpublisher.app.App
import ua.com.netmaster.eventpublisher.databinding.PublisherMainFragmentBinding
import ua.com.netmaster.eventpublisher.model.Event

class PublisherMainFragment : Fragment() {

    companion object {
        fun newInstance() = PublisherMainFragment()
    }

    private lateinit var binding: PublisherMainFragmentBinding
    private lateinit var viewModel: PublisherMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PublisherMainFragmentBinding.inflate(inflater)
        binding.publishEventBtn.setOnClickListener { viewModel.postEvent(createRandomEvent()) }

        //emulate multiple events
        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
            for (i in 1..100) {
                viewModel.postEvent(createRandomEvent())
                delay(10)
            }
        }
        return binding.root
    }

    private fun createRandomEvent(): Event {
        return Event(getRandomString(10), getRandomString(15), System.currentTimeMillis())
    }

    private fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            PublisherMainViewModelFactory(app = requireActivity().application as App)
        )[PublisherMainViewModel::class.java]
    }

}