package uz.suhrob.recipeappkmm.shared.domain.interactor

import uz.suhrob.recipeappkmm.shared.data.database.mapper.RecipeEntityMapper
import uz.suhrob.recipeappkmm.shared.domain.model.Recipe
import uz.suhrob.recipeappkmm.shared.domain.repository.SavedRecipesRepository

class InsertRecipe(
    private val savedRecipesRepository: SavedRecipesRepository,
    private val recipeMapper: RecipeEntityMapper,
) {
    operator fun invoke(recipe: Recipe) {
        savedRecipesRepository.insertRecipe(recipeMapper.fromDomain(recipe))
    }
}