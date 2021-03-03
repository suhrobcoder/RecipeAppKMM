package uz.suhrob.recipeappkmm.shared.network.response

import kotlinx.serialization.Serializable
import uz.suhrob.recipeappkmm.shared.network.model.RecipeDto

@Serializable
data class RandomRecipeResponse(
    val recipes: List<RecipeDto>,
)