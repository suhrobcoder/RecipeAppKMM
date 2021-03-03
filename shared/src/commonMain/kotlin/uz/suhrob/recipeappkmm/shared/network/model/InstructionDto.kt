package uz.suhrob.recipeappkmm.shared.network.model

import kotlinx.serialization.Serializable

@Serializable
data class InstructionDto(
    val name: String,
    val steps: List<InstructionStepDto>,
)