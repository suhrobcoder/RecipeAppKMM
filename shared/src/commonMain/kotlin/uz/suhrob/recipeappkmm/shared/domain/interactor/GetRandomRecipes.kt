package uz.suhrob.recipeappkmm.shared.domain.interactor

import uz.suhrob.recipeappkmm.shared.data.network.mapper.RecipeDtoMapper
import uz.suhrob.recipeappkmm.shared.domain.model.Recipe
import uz.suhrob.recipeappkmm.shared.domain.repository.RecipeRepository
import uz.suhrob.recipeappkmm.shared.util.DEFAULT_PAGE
import uz.suhrob.recipeappkmm.shared.util.RECIPES_PER_PAGE

class GetRandomRecipes(
    private val recipeRepository: RecipeRepository,
    private val recipeDtoMapper: RecipeDtoMapper,
) {
    suspend operator fun invoke(
        tags: List<String> = listOf(),
        page: Int = DEFAULT_PAGE,
    ): List<Recipe> {
        return recipeDtoMapper.toDomainList(
            recipeRepository.getRandomRecipes(
                RECIPES_PER_PAGE,
                tags,
                RECIPES_PER_PAGE * (page - 1),
            )
        )
    }
}