package ua.com.netmaster.eventpublisher.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ua.com.netmaster.eventpublisher.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerCl, PublisherMainFragment.newInstance())
            .commit()
    }
}