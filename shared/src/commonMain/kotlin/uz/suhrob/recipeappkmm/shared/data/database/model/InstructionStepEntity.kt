package uz.suhrob.recipeappkmm.shared.data.database.model

data class InstructionStepEntity(
    val id: Long,
    val recipeId: Long,
    val number: Long,
    val step: String,
    var equipments: List<EquipmentEntity> = listOf(),
    var ingredients: List<IngredientEntity> = listOf(),
)
