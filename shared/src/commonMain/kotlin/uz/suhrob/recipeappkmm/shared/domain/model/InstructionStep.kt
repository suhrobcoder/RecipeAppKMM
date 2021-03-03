package uz.suhrob.recipeappkmm.shared.domain.model

data class InstructionStep(
    val number: Int,
    val step: String,
    val ingredients: List<Ingredient>,
    val equipment: List<Equipment>,
)