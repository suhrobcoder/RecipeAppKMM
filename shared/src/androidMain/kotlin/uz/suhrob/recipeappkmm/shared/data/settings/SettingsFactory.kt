package uz.suhrob.recipeappkmm.shared.data.settings

import android.content.Context
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings

actual class SettingsFactory(private val context: Context) {
    actual fun createSettings(): Settings {
        return AndroidSettings(context.getSharedPreferences("recipe_preferences", Context.MODE_PRIVATE))
    }
}