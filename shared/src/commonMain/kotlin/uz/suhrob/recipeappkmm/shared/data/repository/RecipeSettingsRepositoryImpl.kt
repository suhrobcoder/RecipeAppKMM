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

    override fun isFirstRun(): Boolean {
        val isFirst = settings.getBoolean(KEY_FIRST_RUN, true)
        if (isFirst) {
            settings.putBoolean(KEY_FIRST_RUN, false)
        }
        return isFirst
    }

    companion object {
        private const val KEY_DIETS = "diets"
        private const val KEY_CUISINES = "cuisines"
        private const val KEY_FIRST_RUN = "first_run"
    }

    private fun List<String>.listToString(): String {
        return this.joinToString(separator = "|")
    }

    private fun String.toList(): List<String> {
        return if (this.isEmpty()) listOf() else this.split("|")
    }
}