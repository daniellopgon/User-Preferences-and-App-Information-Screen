package com.example.settings.features.settings.presentation

import android.os.Bundle
import android.util.Log
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.example.settings.R

class MySettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        findPreference<SwitchPreferenceCompat>("news")
            ?.setOnPreferenceChangeListener { _, newValue ->
                Log.d("news", "news enabled: $newValue")
                true
            }

        findPreference<Preference>("developers")
            ?.setOnPreferenceChangeListener { _, newValue ->
                Log.d("Developers", "Developers enabled: $newValue")
                true
            }

        findPreference<Preference>("externalResources")
            ?.setOnPreferenceClickListener {
                Log.d("externalResources", "external Resources enabled")
                true
            }

        findPreference<Preference>("projectWebsite")
            ?.setOnPreferenceClickListener {
                Log.d("projectWebsite", "projectWebsite")
                true
            }

        findPreference<Preference>("contactEmail")
            ?.setOnPreferenceClickListener {
                Log.d("contactEmail", "contactEmail")
                true
            }

        findPreference<Preference>("shareApplication")
            ?.setOnPreferenceClickListener {
                Log.d("shareApplication", "enable share Application")
                true
            }

        findPreference<Preference>("privacyPolicy")
            ?.setOnPreferenceClickListener {
                Log.d("privacyPolicy", "privacy Policy enabled")
                true
            }

        findPreference<Preference>("installedVersion")
            ?.setOnPreferenceClickListener {
                Log.d("installedVersion", "installed Version enabled")
                true
            }

        findPreference<Preference>("dataVersion")
            ?.setOnPreferenceClickListener {
                Log.d("dataVersion", "data Version enabled")
                true
            }
    }
}