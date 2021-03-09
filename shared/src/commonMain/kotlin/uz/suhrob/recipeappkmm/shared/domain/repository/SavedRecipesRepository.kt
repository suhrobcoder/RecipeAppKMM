package uz.suhrob.recipeappkmm.shared.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.suhrob.recipeappkmm.shared.data.database.model.RecipeEntity

interface SavedRecipesRepository {
    fun insertRecipe(recipe: RecipeEntity)

    fun deleteRecipe(id: Int)

    fun getAllRecipes(): Flow<List<RecipeEntity>>

    fun getRecipeById(id: Int): RecipeEntity
}