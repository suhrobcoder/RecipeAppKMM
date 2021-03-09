package uz.suhrob.recipeappkmm.shared.data.settings

import android.content.Context
import com.russhwolf.settings.AndroidSettings
import uz.suhrob.recipeappkmm.shared.data.repository.RecipeSettingsRepositoryImpl
import uz.suhrob.recipeappkmm.shared.domain.repository.RecipeSettingsRepository

actual class SettingsFactory(private val context: Context) {
    actual fun createSettings(): RecipeSettingsRepository {
        return RecipeSettingsRepositoryImpl(
            AndroidSettings(
                context.getSharedPreferences(
                    "recipe_preferences",
                    Context.MODE_PRIVATE
                )
            )
        )
    }
}