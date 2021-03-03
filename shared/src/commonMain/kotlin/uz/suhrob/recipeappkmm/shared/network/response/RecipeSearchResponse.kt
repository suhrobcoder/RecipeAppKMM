package uz.suhrob.recipeappkmm.shared.network.response

import kotlinx.serialization.Serializable
import uz.suhrob.recipeappkmm.shared.network.model.RecipeSearchItemDto

@Serializable
data class RecipeSearchResponse(
    val results: List<RecipeSearchItemDto>,
    val offset: Int,
    val number: Int,
    val totalResults: Int,
)