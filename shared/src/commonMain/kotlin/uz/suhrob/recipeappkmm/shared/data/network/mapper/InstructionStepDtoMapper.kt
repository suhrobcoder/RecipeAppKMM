package uz.suhrob.recipeappkmm.shared.data.network.mapper

import uz.suhrob.recipeappkmm.shared.data.network.model.InstructionStepDto
import uz.suhrob.recipeappkmm.shared.domain.model.InstructionStep
import uz.suhrob.recipeappkmm.shared.domain.util.BaseMapper

class InstructionStepDtoMapper(
    private val equipmentMapper: EquipmentDtoMapper,
    private val ingredientMapper: IngredientDtoMapper
) : BaseMapper<InstructionStepDto, InstructionStep> {
    override fun toDomain(model: InstructionStepDto): InstructionStep {
        return InstructionStep(
            step = model.step,
            number = model.number,
            equipment = equipmentMapper.toDomainList(model.equipment),
            ingredients = ingredientMapper.toDomainList(model.ingredients),
        )
    }

    override fun fromDomain(model: InstructionStep): InstructionStepDto {
        return InstructionStepDto(
            step = model.step,
            number = model.number,
            equipment = equipmentMapper.fromDomainList(model.equipment),
            ingredients = ingredientMapper.fromDomainList(model.ingredients),
        )
    }
}