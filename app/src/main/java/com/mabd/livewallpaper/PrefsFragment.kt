package com.mabd.livewallpaper

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.preference.PreferenceFragmentCompat


class PrefsFragment : PreferenceFragmentCompat() {

    
    companion object {
        const val TAG = "PrefsFragmentTag"
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        Log.d(TAG, "onCreatePreferences: ")
        setPreferencesFromResource(R.xml.prefs, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        Log.d(TAG, "onViewCreated: ")
    }
    


}