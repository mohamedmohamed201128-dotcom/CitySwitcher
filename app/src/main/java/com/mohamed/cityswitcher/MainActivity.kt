package com.mohamed.cityswitcher

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    private data class City(val label: String, val zone: String, val detail: String)

    private val cities = listOf(
        City("Texas (Central Time)", "America/Chicago",
            "Texas uses more than one time zone. This option is for most major cities, including Dallas, Houston, Austin and San Antonio."),
        City("Los Angeles (Pacific Time)", "America/Los_Angeles",
            "Pacific Time. Android applies the correct daylight-saving rules automatically."),
        City("Egypt (Cairo Time)", "Africa/Cairo",
            "Returns the phone's time zone to Cairo, Egypt.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(28, 36, 28, 24)
            gravity = Gravity.CENTER_HORIZONTAL
        }

        val title = TextView(this).apply {
            text = "City Switcher"
            textSize = 26f
            gravity = Gravity.CENTER
        }
        root.addView(title, LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ))

        val subtitle = TextView(this).apply {
            text = "Choose the time zone you want to set"
            textSize = 15f
            gravity = Gravity.CENTER
            setPadding(0, 12, 0, 24)
        }
        root.addView(subtitle)

        cities.forEach { city ->
            val button = Button(this).apply {
                text = city.label
                isAllCaps = false
                setOnClickListener { showCityInstructions(city) }
            }
            root.addView(button, LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply { bottomMargin = 10 })
        }

        val locationButton = Button(this).apply {
            text = "Open Location Settings"
            isAllCaps = false
            setOnClickListener {
                try {
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                } catch (_: Exception) {
                    showMessage("Location settings are not available on this device.")
                }
            }
        }
        root.addView(locationButton, LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply { topMargin = 16 })

        val note = TextView(this).apply {
            text = "This app does not change your proxy, IP address or GPS location. Android requires you to choose the time zone manually in system settings."
            textSize = 13f
            setPadding(4, 24, 4, 0)
        }
        root.addView(note)

        setContentView(root)
    }

    private fun showCityInstructions(city: City) {
        AlertDialog.Builder(this)
            .setTitle(city.label)
            .setMessage(
                "${city.detail}\n\nTime zone ID:\n${city.zone}\n\n" +
                "Next, Android will open Date & time settings. Choose the matching time zone there. " +
                "Keep 'Set time automatically' enabled so the clock follows network time."
            )
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Open Date & time") { _, _ ->
                try {
                    startActivity(Intent(Settings.ACTION_DATE_SETTINGS))
                } catch (_: Exception) {
                    showMessage("Date & time settings could not be opened automatically.")
                }
            }
            .show()
    }

    private fun showMessage(message: String) {
        AlertDialog.Builder(this).setMessage(message).setPositiveButton("OK", null).show()
    }
}
