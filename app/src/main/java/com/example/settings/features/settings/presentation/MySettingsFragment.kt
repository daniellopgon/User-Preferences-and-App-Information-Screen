package com.example.settings.features.settings.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.example.settings.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
import kotlin.collections.map

class MySettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        setupNotifications()
        setupDevelopers()
        setupExternalResources()
        setupProjectWebsite()
        setupContactEmail()
        setupShareApp()
        setupPrivacyPolicy()
        setupVersionInfo()
        setupDataVersion()
    }

    private fun setupNotifications() {
        findPreference<SwitchPreferenceCompat>("notifications_news")
            ?.setOnPreferenceChangeListener { _, newValue ->

                val isEnabled = newValue as Boolean

                if (isEnabled) {
                    Toast.makeText(requireContext(), "Notificaciones activadas", Toast.LENGTH_SHORT).show()
                    Log.d("Preferences", "Notificaciones activadas - SIMULADO")
                } else {
                    Toast.makeText(requireContext(), "Notificaciones desactivadas", Toast.LENGTH_SHORT).show()
                    Log.d("Preferences", "Notificaciones desactivadas - SIMULADO")
                }
                true
            }
    }

    private fun setupDevelopers() {
        findPreference<Preference>("developers")?.setOnPreferenceClickListener {
            showDevelopersDialog()
            true
        }
    }

    private fun showDevelopersDialog() {
        fetchDevelopersFromAPI { developers ->
            val items = developers.map { "${it.name}\n${it.title}" }.toTypedArray()

            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Desarrolladores")
                .setItems(items) { _, which ->
                    openWebPage(developers[which].linkedinUrl)
                }
                .setPositiveButton("Cerrar", null)
                .show()
        }
    }

    private fun setupExternalResources() {
        findPreference<Preference>("external_resources")?.setOnPreferenceClickListener {
            showResourcesDialog()
            true
        }
    }

    private fun showResourcesDialog() {
        fetchResourcesFromAPI { resources ->
            val items = resources.map { it.name }.toTypedArray()

            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Recursos Externos")
                .setItems(items) { _, which ->
                    openWebPage(resources[which].url)
                }
                .setPositiveButton("Cerrar", null)
                .show()
        }
    }

    private fun setupProjectWebsite() {
        findPreference<Preference>("web_project")?.setOnPreferenceClickListener {
            openWebPage("https://github.com/daniellopgon")
            true
        }
    }

    private fun setupContactEmail() {
        findPreference<Preference>("contact_email")?.setOnPreferenceClickListener {
            composeEmail(
                addresses = arrayOf("daniel.lopgon.4@gmail.com"),
                subject = "Información sobre la App"
            )
            true
        }
    }

    private fun setupShareApp() {
        findPreference<Preference>("share_app")?.setOnPreferenceClickListener {
            shareAppLink()
            true
        }
    }

    private fun setupPrivacyPolicy() {
        findPreference<Preference>("privacy_policy")?.setOnPreferenceClickListener {
            openWebPage("https://github.com/daniellopgon")
            true
        }
    }

    private fun setupVersionInfo() {
        findPreference<Preference>("version")?.apply {
            try {
                val packageInfo = requireContext().packageManager.getPackageInfo(
                    requireContext().packageName, 0
                )
                summary = "v${packageInfo.versionName}"
            } catch (e: Exception) {
                summary = "Desconocida"
                Log.e("Preferences", "Error obteniendo versión", e)
            }
        }
    }

    private fun setupDataVersion() {
        findPreference<Preference>("data_date")?.apply {
            summary = "Cargando..."
            fetchDataVersionFromAPI { dateVersion ->
                summary = dateVersion
            }
        }
    }

    private fun openWebPage(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            showToast("No se puede abrir el enlace")
            Log.e("Preferences", "Error abriendo URL: $url", e)
        }
    }

    private fun composeEmail(addresses: Array<String>, subject: String) {
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, addresses)
                putExtra(Intent.EXTRA_SUBJECT, subject)
            }
            startActivity(intent)
        } catch (e: Exception) {
            showToast("No hay cliente de correo disponible")
        }
    }

    private fun shareAppLink() {
        try {
            val playStoreLink = "https://play.google.com/store/apps/details?id=${requireContext().packageName}"
            val shareText = "Descarga la App FP del IES Alonso de Madrigal: $playStoreLink"

            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareText)
                putExtra(Intent.EXTRA_SUBJECT, "App FP IES Alonso de Madrigal")
            }

            startActivity(Intent.createChooser(intent, "Compartir aplicación"))
        } catch (e: Exception) {
            showToast("No se puede compartir la aplicación")
        }
    }


    private fun fetchDevelopersFromAPI(callback: (List<Developers>) -> Unit) {
        lifecycleScope.launch {
            try {
                // TODO: Reemplazar con llamada real a API
                val mockDevelopers = listOf(
                    Developers(
                        name = "Daniel",
                        title = "Desarrollador software junior",
                        linkedinUrl = "https://www.linkedin.com/in/dani-418",
                        imageUrl = "https://via.placeholder.com/150"
                    ),
                    Developers(
                        name = "Marcos",
                        title = "Desarrollador software junior",
                        linkedinUrl = "https://www.linkedin.com/in/null",
                        imageUrl = "https://via.placeholder.com/150"
                    ),
                    Developers(
                        name = "Juan",
                        title = "Desarrollador software junior",
                        linkedinUrl = "https://www.linkedin.com/in/null",
                        imageUrl = "https://via.placeholder.com/150"
                    )
                )
                callback(mockDevelopers)
            } catch (e: Exception) {
                Log.e("Preferences", "Error fetching developers", e)
                showToast("Error al cargar desarrolladores")
                callback(emptyList())
            }
        }
    }

    private fun fetchResourcesFromAPI(callback: (List<ExternalResource>) -> Unit) {
        lifecycleScope.launch {
            try {
                // TODO: Reemplazar con llamada real a API
                val mockResources = listOf(
                    ExternalResource(
                        name = "Firebase",
                        description = "Backend as a Service",
                        url = "https://firebase.google.com",
                        imageUrl = "https://via.placeholder.com/150"
                    ),
                )
                callback(mockResources)
            } catch (e: Exception) {
                Log.e("Preferences", "Error fetching resources", e)
                showToast("Error al cargar recursos")
                callback(emptyList())
            }
        }
    }

    private fun fetchDataVersionFromAPI(callback: (String) -> Unit) {
        lifecycleScope.launch {
            try {
                // TODO: Reemplazar con llamada real a API
                callback("Última actualización: 09/11/2025")
            } catch (e: Exception) {
                Log.e("Preferences", "Error fetching data version", e)
                callback("Error al obtener fecha")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}