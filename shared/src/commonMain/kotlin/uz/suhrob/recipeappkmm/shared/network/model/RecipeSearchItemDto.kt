package uz.suhrob.recipeappkmm.shared.network.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeSearchItemDto(
    val id: Int,
    val title: String,
    val image: String? = null,
)