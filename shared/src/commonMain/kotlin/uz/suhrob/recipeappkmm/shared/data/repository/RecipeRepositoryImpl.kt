package uz.suhrob.recipeappkmm.shared.data.repository

import uz.suhrob.recipeappkmm.shared.data.network.LastPageReachedException
import uz.suhrob.recipeappkmm.shared.data.network.RecipeApi
import uz.suhrob.recipeappkmm.shared.data.network.model.RecipeDto
import uz.suhrob.recipeappkmm.shared.data.network.model.RecipeSearchItemDto
import uz.suhrob.recipeappkmm.shared.domain.repository.RecipeRepository

class RecipeRepositoryImpl(
    private val recipeApi: RecipeApi
) : RecipeRepository {
    override suspend fun getRecipeById(id: Int): RecipeDto {
        return recipeApi.getRecipeById(id = id)
    }

    override suspend fun searchRecipes(
        query: String,
        cuisine: List<String>,
        diet: String,
        includeIngredients: List<String>,
        maxReadyTime: Int,
        sort: String,
        sortDirection: String,
        offset: Int,
    ): List<RecipeSearchItemDto> {
        val res = recipeApi.searchRecipes(
            query,
            cuisine,
            diet,
            includeIngredients,
            maxReadyTime,
            sort,
            sortDirection,
            offset,
        )
        if (res.offset >= res.totalResults) {
            throw LastPageReachedException()
        }
        return res.results
    }

    override suspend fun getRandomRecipes(
        number: Int,
        tags: List<String>,
        offset: Int,
    ): List<RecipeDto> {
        val res = recipeApi.getRandomRecipes(number, tags, offset)
        if (res.recipes.isEmpty()) {
            throw LastPageReachedException()
        }
        return res.recipes
    }

    override suspend fun autoCompleteRecipeSearch(
        query: String,
        number: Int
    ): List<RecipeSearchItemDto> {
        return recipeApi.autoCompleteRecipeSearch(query, number)
    }
}