package uz.suhrob.recipeappkmm.shared.domain.repository

interface RecipeSettingsRepository {
    fun setDiets(diets: List<String>)

    fun setCuisines(cuisines: List<String>)

    fun getDiets(): List<String>

    fun getCuisines(): List<String>

    fun isFirstRun(): Boolean
}