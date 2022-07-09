package com.mabd.livewallpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mabd.livewallpaper.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager
            .beginTransaction()
            .replace(binding.frameLayout.id, PrefsFragment()).commit()



    }
}