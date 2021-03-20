package uz.suhrob.recipeappkmm.shared.domain.interactor

import uz.suhrob.recipeappkmm.shared.domain.repository.RecipeSettingsRepository

class SetCuisines(
    private val settingsRepository: RecipeSettingsRepository,
) {
    operator fun invoke(cuisines: List<String>) {
        settingsRepository.setCuisines(cuisines)
    }
}