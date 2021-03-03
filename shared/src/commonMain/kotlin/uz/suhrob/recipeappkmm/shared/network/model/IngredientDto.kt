package uz.suhrob.recipeappkmm.shared.network.model

import kotlinx.serialization.Serializable

@Serializable
data class IngredientDto(
    val id: Int,
    val name: String,
    val image: String?,
)