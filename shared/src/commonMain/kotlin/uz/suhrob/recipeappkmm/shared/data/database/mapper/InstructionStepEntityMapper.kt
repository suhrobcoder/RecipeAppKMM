package uz.suhrob.recipeappkmm.shared.data.database.mapper

import uz.suhrob.recipeappkmm.shared.data.database.model.EquipmentEntity
import uz.suhrob.recipeappkmm.shared.data.database.model.IngredientEntity
import uz.suhrob.recipeappkmm.shared.data.database.model.InstructionStepEntity
import uz.suhrob.recipeappkmm.shared.domain.model.Equipment
import uz.suhrob.recipeappkmm.shared.domain.model.Ingredient
import uz.suhrob.recipeappkmm.shared.domain.model.InstructionStep
import uz.suhrob.recipeappkmm.shared.domain.util.BaseMapper

class InstructionStepEntityMapper : BaseMapper<InstructionStepEntity, InstructionStep> {
    override fun toDomain(model: InstructionStepEntity): InstructionStep {
        val ingredients = model.ingredients.map { entity ->
            Ingredient(
                id = entity.id.toInt(),
                name = entity.name,
                image = entity.image,
            )
        }
        val equipments = model.equipments.map { entity ->
            Equipment(
                id = entity.id.toInt(),
                name = entity.name,
                image = entity.image,
            )
        }
        return InstructionStep(
            number = model.number.toInt(),
            step = model.step,
            ingredients = ingredients,
            equipment = equipments,
        )
    }

    override fun fromDomain(model: InstructionStep): InstructionStepEntity {
        val ingredients = model.ingredients.map { ingredient ->
            IngredientEntity(
                id = ingredient.id.toLong(),
                instructionStepId = 0,
                name = ingredient.name,
                image = ingredient.image,
            )
        }
        val equipments = model.equipment.map { equipment ->
            EquipmentEntity(
                id = equipment.id.toLong(),
                instructionStepId = 0,
                name = equipment.name,
                image = equipment.image,
            )
        }
        return InstructionStepEntity(
            id = 0L,
            recipeId = 0L,
            number = model.number.toLong(),
            step = model.step,
            equipments = equipments,
            ingredients = ingredients,
        )
    }
}