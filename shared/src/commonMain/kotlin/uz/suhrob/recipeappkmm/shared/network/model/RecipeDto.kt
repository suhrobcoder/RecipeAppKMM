package uz.suhrob.recipeappkmm.shared.network.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    val id: Int,
    val title: String,
    val summary: String,
    val image: String?,
    val vegetarian: Boolean,
    val readyInMinutes: Int?,
    val instructions: String?,
    val cuisines: List<String> = listOf(),
    val diets: List<String> = listOf(),
    val analyzedInstructions: List<InstructionDto>,
)
