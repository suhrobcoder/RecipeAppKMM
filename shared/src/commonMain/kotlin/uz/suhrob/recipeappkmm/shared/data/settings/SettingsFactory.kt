package uz.suhrob.recipeappkmm.shared.data.settings

import uz.suhrob.recipeappkmm.shared.domain.repository.RecipeSettingsRepository

expect class SettingsFactory {
    fun createSettings(): RecipeSettingsRepository
}