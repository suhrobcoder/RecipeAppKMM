package uz.suhrob.recipeappkmm.shared.data.network.mapper

import uz.suhrob.recipeappkmm.shared.data.network.model.EquipmentDto
import uz.suhrob.recipeappkmm.shared.domain.model.Equipment
import uz.suhrob.recipeappkmm.shared.domain.util.BaseMapper

class EquipmentDtoMapper : BaseMapper<EquipmentDto, Equipment> {
    override fun toDomain(model: EquipmentDto): Equipment {
        return Equipment(
            id = model.id,
            name = model.name,
            image = model.image,
        )
    }

    override fun fromDomain(model: Equipment): EquipmentDto {
        return EquipmentDto(
            id = model.id,
            name = model.name,
            image = model.image,
        )
    }
}