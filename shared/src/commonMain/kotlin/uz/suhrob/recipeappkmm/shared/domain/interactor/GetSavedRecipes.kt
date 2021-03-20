package uz.suhrob.recipeappkmm.shared.domain.interactor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.suhrob.recipeappkmm.shared.data.database.mapper.RecipeEntityMapper
import uz.suhrob.recipeappkmm.shared.domain.model.Recipe
import uz.suhrob.recipeappkmm.shared.domain.repository.SavedRecipesRepository

class GetSavedRecipes(
    private val savedRecipesRepository: SavedRecipesRepository,
    private val recipeMapper: RecipeEntityMapper,
) {
    operator fun invoke(): Flow<List<Recipe>> {
        return savedRecipesRepository.getAllRecipes().map { recipeMapper.toDomainList(it) }
    }
}