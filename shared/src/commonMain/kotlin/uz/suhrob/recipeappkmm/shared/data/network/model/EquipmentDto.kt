package uz.suhrob.recipeappkmm.shared.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class EquipmentDto(
    val id: Int,
    val name: String,
    val image: String?,
)