package main.java.com.example.settings.features.settings.presentation

import android.os.Bundle
import android.util.Log
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat

class MySettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(
        savedInstanceState: Bundle?,
        rootKey: String?
    ) {

        val news = findPreference<SwitchPreferenceCompat>("news")
            ?.setOnPreferenceChangeListener { _, newValue ->
                Log.d("news", "news enabled: $newValue")
                true
            }

        val developers = findPreference<Preference>("developers")
            ?.setOnPreferenceChangeListener { _, newValue ->
                Log.d("Developers", "Developers enabled: $newValue")
                true
            }

        val externalResources = findPreference<Preference>("externalResources")
            ?.setOnPreferenceClickListener  {
                Log.d("externalResources", "external Resources enabled")
                true
            }

        val projectWebsite = findPreference<Preference>("projectWebsite")
            ?.setOnPreferenceClickListener  {
                Log.d("projectWebsite", "projectWebsite")
                true
            }

        val contactEmail = findPreference<Preference>("contactEmail")
            ?.setOnPreferenceClickListener  {
                Log.d("contactEmail", "contactEmail")
                true
            }

        val shareApplication = findPreference<Preference>("shareApplication")
            ?.setOnPreferenceClickListener  {
                Log.d("shareApplication", "enable share Application")
                true
            }

        val privacyPolicy = findPreference<Preference>("privacyPolicy")
            ?.setOnPreferenceClickListener  {
                Log.d("privacyPolicy", "privacy Policy enabled")
                true
            }

        val  installedVersion = findPreference<Preference>("installedVersion")
            ?.setOnPreferenceClickListener  {
                Log.d("installedVersion", "installed Version enabled")
                true
            }

        val dataVersion = findPreference<Preference>("dataVersion")
            ?.setOnPreferenceClickListener  {
                Log.d("dataVersion", "data Version enabled")
                true
            }
    }
}