package uz.suhrob.recipeappkmm.shared.domain.interactor

import uz.suhrob.recipeappkmm.shared.domain.repository.SavedRecipesRepository

class DeleteRecipe(
    private val savedRecipesRepository: SavedRecipesRepository,
) {
    operator fun invoke(id: Int) {
        savedRecipesRepository.deleteRecipe(id = id)
    }
}