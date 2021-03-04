package uz.suhrob.recipeappkmm.shared.data.database.model

data class RecipeEntity(
    val id: Long,
    val title: String,
    val summary: String,
    val image: String?,
    val vegetarian: Long,
    val readyInMinutes: Long?,
    val instructions: String?,
    val cuisines: String,
    val diets: String,
)