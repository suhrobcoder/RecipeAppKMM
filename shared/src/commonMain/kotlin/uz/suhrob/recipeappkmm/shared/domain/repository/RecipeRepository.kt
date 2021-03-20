package uz.suhrob.recipeappkmm.shared.domain.repository

import uz.suhrob.recipeappkmm.shared.data.network.model.RecipeDto
import uz.suhrob.recipeappkmm.shared.data.network.model.RecipeSearchItemDto

interface RecipeRepository {
    suspend fun getRecipeById(id: Int): RecipeDto

    suspend fun searchRecipes(
        query: String,
        cuisine: List<String>,
        diet: String,
        includeIngredients: List<String>,
        maxReadyTime: Int,
        sort: String,
        sortDirection: String,
        number: Int,
        offset: Int,
    ): List<RecipeSearchItemDto>

    suspend fun getRandomRecipes(
        number: Int,
        tags: List<String> = listOf(),
        offset: Int,
    ): List<RecipeDto>

    suspend fun autoCompleteRecipeSearch(
        query: String,
        number: Int,
    ): List<RecipeSearchItemDto>
}