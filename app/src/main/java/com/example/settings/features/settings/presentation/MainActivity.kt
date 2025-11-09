package main.java.com.example.settings.features.settings.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.iesam.examen.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_container, MySettingsFragment())
            .commit()
    }
}
