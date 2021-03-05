package uz.suhrob.recipeappkmm.shared.data.settings

import com.russhwolf.settings.Settings
import com.russhwolf.settings.AppleSettings
import platform.Foundation.NSUserDefaults

actual class SettingsFactory {
    actual fun createSettings(): Settings {
        return AppleSettings(NSUserDefaults.standardUserDefaults)
    }
}