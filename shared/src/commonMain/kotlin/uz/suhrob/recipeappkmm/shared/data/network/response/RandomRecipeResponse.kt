package uz.suhrob.recipeappkmm.shared.data.network.response

import kotlinx.serialization.Serializable
import uz.suhrob.recipeappkmm.shared.data.network.model.RecipeDto

@Serializable
data class RandomRecipeResponse(
    val recipes: List<RecipeDto>,
)