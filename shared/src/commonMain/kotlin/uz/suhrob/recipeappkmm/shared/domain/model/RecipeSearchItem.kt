package uz.suhrob.recipeappkmm.shared.domain.model

data class RecipeSearchItem(
    val id: Int,
    val title: String,
    val image: String? = null,
)
