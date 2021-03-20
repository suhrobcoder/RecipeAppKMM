package uz.suhrob.recipeappkmm.shared.data.database.dao

import uz.suhrob.recipeappkmm.database.RecipeDb
import uz.suhrob.recipeappkmm.shared.data.database.model.EquipmentEntity
import uz.suhrob.recipeappkmm.shared.data.database.model.IngredientEntity
import uz.suhrob.recipeappkmm.shared.data.database.model.InstructionStepEntity

class InstructionsDao(database: RecipeDb) {
    private val dbQueries = database.recipeDbQueries

    private val instructionMapper: (
        id: Long, recipeId: Long, number: Long, step: String
    ) -> InstructionStepEntity = { id, recipeId, number, step ->
        InstructionStepEntity(id, recipeId, number, step)
    }

    private val equipmentMapper: (
        id: Long, instructionStepId: Long, name: String, image: String?
    ) -> EquipmentEntity = { id, instructionStepId, name, image ->
        EquipmentEntity(id, instructionStepId, name, image)
    }

    private val ingredientMapper: (
        id: Long, instructionStepId: Long, name: String, image: String?
    ) -> IngredientEntity = { id, instructionStepId, name, image ->
        IngredientEntity(id, instructionStepId, name, image)
    }

    fun insertInstructions(instructions: List<InstructionStepEntity>, recipeId: Long) {
        for (instruction in instructions) {
            val id = recipeId * 100 + instruction.number
            dbQueries.insertInstructionStep(
                recipeId = recipeId,
                number = instruction.number,
                step = instruction.step
            )
            insertEquipments(instruction.equipments, id)
            insertIngredients(instruction.ingredients, id)
        }
    }

    fun getInstructionStepsByRecipeId(recipeId: Long): List<InstructionStepEntity> {
        val instructions = dbQueries.getInstructionStepsByRecipeId(recipeId, instructionMapper).executeAsList()
        for (i in instructions.indices) {
            instructions[i].ingredients = getIngredientByStepId(instructions[i].id)
            instructions[i].equipments = getEquipmentsByStepId(instructions[i].id)
        }
        return instructions
    }

    fun deleteInstructionsByRecipeId(recipeId: Long) {
        val instructions = dbQueries.getInstructionStepsByRecipeId(recipeId, instructionMapper).executeAsList()
        for (instruction in instructions) {
            dbQueries.deleteEquipmentsByStepId(instruction.id)
            dbQueries.deleteIngredientByStepId(instruction.id)
        }
        dbQueries.deleteInstructionsByRecipeId(recipeId)
    }

    private fun insertEquipments(equipments: List<EquipmentEntity>, stepId: Long) {
        for (equipment in equipments) {
            with(equipment) {
                dbQueries.insertEquipment(stepId, name, image)
            }
        }
    }

    private fun getEquipmentsByStepId(stepId: Long): List<EquipmentEntity> {
        return dbQueries.getEquipmentsByStepId(stepId, equipmentMapper).executeAsList()
    }

    private fun insertIngredients(ingredients: List<IngredientEntity>, stepId: Long) {
        for (ingredient in ingredients) {
            with(ingredient) {
                dbQueries.insertIngredient(stepId, name, image)
            }
        }
    }

    private fun getIngredientByStepId(stepId: Long): List<IngredientEntity> {
        return dbQueries.getIngredientsByStepId(stepId, ingredientMapper).executeAsList()
    }
}