package uz.suhrob.recipeappkmm.shared.data.settings

import com.russhwolf.settings.AppleSettings
import platform.Foundation.NSUserDefaults
import uz.suhrob.recipeappkmm.shared.data.repository.RecipeSettingsRepositoryImpl
import uz.suhrob.recipeappkmm.shared.domain.repository.RecipeSettingsRepository

actual class SettingsFactory {
    actual fun createSettings(): RecipeSettingsRepository {
        return RecipeSettingsRepositoryImpl(AppleSettings(NSUserDefaults.standardUserDefaults))
    }
}