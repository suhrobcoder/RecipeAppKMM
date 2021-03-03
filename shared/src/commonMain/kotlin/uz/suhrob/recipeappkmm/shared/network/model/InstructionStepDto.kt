package uz.suhrob.recipeappkmm.shared.network.model

import kotlinx.serialization.Serializable

@Serializable
data class InstructionStepDto(
    val number: Int,
    val step: String,
    val ingredients: List<IngredientDto>,
    val equipment: List<EquipmentDto>,
)