package uz.suhrob.recipeappkmm.shared.domain.interactor

import uz.suhrob.recipeappkmm.shared.domain.repository.RecipeSettingsRepository

class GetCuisines(
    private val settingsRepository: RecipeSettingsRepository,
) {
    operator fun invoke(): List<String> {
        return settingsRepository.getCuisines()
    }
}