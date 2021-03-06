package uz.suhrob.recipeappkmm.shared.data.repository

import com.russhwolf.settings.Settings
import uz.suhrob.recipeappkmm.shared.domain.repository.RecipeSettingsRepository

class RecipeSettingsRepositoryImpl(
    private val settings: Settings
) : RecipeSettingsRepository {
    override fun setDiets(diets: List<String>) {
        settings.putString(KEY_DIETS, diets.listToString())
    }

    override fun setCuisines(cuisines: List<String>) {
        settings.putString(KEY_CUISINES, cuisines.listToString())
    }

    override fun getCuisines(): List<String> {
        return settings.getString(KEY_CUISINES).toList()
    }

    override fun getDiets(): List<String> {
        return settings.getString(KEY_DIETS).toList()
    }

    companion object {
        private const val KEY_DIETS = "diets"
        private const val KEY_CUISINES = "cuisines"
    }

    private fun List<String>.listToString(): String {
        return this.joinToString(separator = "|")
    }

    private fun String.toList(): List<String> {
        return this.split("|")
    }
}