package uz.suhrob.recipeappkmm.shared.domain.interactor

import uz.suhrob.recipeappkmm.shared.data.network.mapper.RecipeSearchItemMapper
import uz.suhrob.recipeappkmm.shared.domain.model.RecipeSearchItem
import uz.suhrob.recipeappkmm.shared.domain.repository.RecipeRepository
import uz.suhrob.recipeappkmm.shared.util.DEFAULT_PAGE
import uz.suhrob.recipeappkmm.shared.util.RECIPES_PER_PAGE

class SearchRecipes(
    private val recipeRepository: RecipeRepository,
    private val recipeSearchItemMapper: RecipeSearchItemMapper
) {
    suspend operator fun invoke(
        query: String,
        cuisine: List<String>,
        diet: String,
        intelorances: String,
        type: String,
        includeIngredients: List<String>,
        maxReadyTime: Int,
        sort: String,
        sortDirection: String,
        page: Int = DEFAULT_PAGE,
    ): List<RecipeSearchItem> {
        return recipeSearchItemMapper.toDomainList(
            recipeRepository.searchRecipes(
                query,
                cuisine,
                diet,
                intelorances,
                type,
                includeIngredients,
                maxReadyTime,
                sort,
                sortDirection,
                RECIPES_PER_PAGE,
                RECIPES_PER_PAGE * (page - 1),
            )
        )
    }
}