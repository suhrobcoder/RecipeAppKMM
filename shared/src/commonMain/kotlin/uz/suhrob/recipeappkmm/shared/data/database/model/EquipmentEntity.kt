package uz.suhrob.recipeappkmm.shared.data.database.model

data class EquipmentEntity(
    val id: Long,
    val instructionStepId: Long,
    val name: String,
    val image: String?,
)