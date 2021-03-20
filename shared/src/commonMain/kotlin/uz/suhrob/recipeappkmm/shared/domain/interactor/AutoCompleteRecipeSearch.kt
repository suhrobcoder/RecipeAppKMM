package uz.suhrob.recipeappkmm.shared.domain.interactor

import uz.suhrob.recipeappkmm.shared.data.network.mapper.RecipeSearchItemMapper
import uz.suhrob.recipeappkmm.shared.domain.model.RecipeSearchItem
import uz.suhrob.recipeappkmm.shared.domain.repository.RecipeRepository
import uz.suhrob.recipeappkmm.shared.util.AUTOCOMPLETE_NUMBER

class AutoCompleteRecipeSearch(
    private val recipeRepository: RecipeRepository,
    private val recipeSearchItemMapper: RecipeSearchItemMapper,
) {
    suspend operator fun invoke(
        query: String,
    ): List<RecipeSearchItem> {
        return recipeSearchItemMapper.toDomainList(
            recipeRepository.autoCompleteRecipeSearch(query, AUTOCOMPLETE_NUMBER)
        )
    }
}