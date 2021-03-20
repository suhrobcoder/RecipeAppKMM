package uz.suhrob.recipeappkmm.shared.domain.interactor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.suhrob.recipeappkmm.shared.data.database.mapper.RecipeEntityMapper
import uz.suhrob.recipeappkmm.shared.data.network.mapper.RecipeDtoMapper
import uz.suhrob.recipeappkmm.shared.domain.data.DataState
import uz.suhrob.recipeappkmm.shared.domain.model.Recipe
import uz.suhrob.recipeappkmm.shared.domain.repository.RecipeRepository
import uz.suhrob.recipeappkmm.shared.domain.repository.SavedRecipesRepository

class GetRecipeById(
    private val recipeRepository: RecipeRepository,
    private val savedRecipesRepository: SavedRecipesRepository,
    private val recipeDtoMapper: RecipeDtoMapper,
    private val recipeEntityMapper: RecipeEntityMapper,
) {
    suspend operator fun invoke(
        id: Int,
    ): Flow<DataState<Recipe>> = flow {
        try {
            emit(DataState.loading<Recipe>())
            val recipe = try {
                getRecipeFromDb(id)
            } catch (e: Exception) {
                getRecipeFromApi(id)
            }
            emit(DataState.success(recipe))
        } catch (e: Exception) {
            emit(DataState.error<Recipe>(message = e.message ?: "Unknown error"))
        }
    }

    private fun getRecipeFromDb(id: Int): Recipe {
        return recipeEntityMapper.toDomain(savedRecipesRepository.getRecipeById(id))
    }

    private suspend fun getRecipeFromApi(id: Int): Recipe {
        return recipeDtoMapper.toDomain(recipeRepository.getRecipeById(id))
    }
}