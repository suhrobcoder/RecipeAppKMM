package uz.suhrob.recipeappkmm.shared.domain.interactor

import uz.suhrob.recipeappkmm.shared.domain.repository.RecipeSettingsRepository

class SetDiets(
    private val settingsRepository: RecipeSettingsRepository,
) {
    operator fun invoke(diets: List<String>) {
        settingsRepository.setDiets(diets)
    }
}