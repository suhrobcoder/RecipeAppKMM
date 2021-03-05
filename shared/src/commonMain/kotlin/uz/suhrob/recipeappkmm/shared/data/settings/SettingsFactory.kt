package uz.suhrob.recipeappkmm.shared.data.settings

import com.russhwolf.settings.Settings

expect class SettingsFactory {
    fun createSettings(): Settings
}