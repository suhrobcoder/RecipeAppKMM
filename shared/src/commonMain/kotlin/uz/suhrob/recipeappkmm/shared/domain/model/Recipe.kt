package uz.suhrob.recipeappkmm.shared.domain.model

data class Recipe(
    val id: Int,
    val title: String,
    val summary: String,
    val image: String?,
    val vegetarian: Boolean,
    val readyInMinutes: Int?,
    val instructions: String?,
    val cuisines: List<String> = listOf(),
    val diets: List<String> = listOf(),
    val instructionSteps: List<InstructionStep>,
)