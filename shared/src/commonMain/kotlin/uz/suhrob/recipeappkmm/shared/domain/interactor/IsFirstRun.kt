package uz.suhrob.recipeappkmm.shared.domain.interactor

import uz.suhrob.recipeappkmm.shared.domain.repository.RecipeSettingsRepository

class IsFirstRun(
    private val settingsRepository: RecipeSettingsRepository,
) {
    operator fun invoke(): Boolean {
        return settingsRepository.isFirstRun()
    }
}