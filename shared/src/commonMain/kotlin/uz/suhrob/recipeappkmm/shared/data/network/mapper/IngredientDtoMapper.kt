package uz.suhrob.recipeappkmm.shared.data.network.mapper

import uz.suhrob.recipeappkmm.shared.data.network.model.IngredientDto
import uz.suhrob.recipeappkmm.shared.domain.model.Ingredient
import uz.suhrob.recipeappkmm.shared.domain.util.BaseMapper

class IngredientDtoMapper : BaseMapper<IngredientDto, Ingredient> {
    override fun toDomain(model: IngredientDto): Ingredient {
        return Ingredient(
            id = model.id,
            name = model.name,
            image = model.image,
        )
    }

    override fun fromDomain(model: Ingredient): IngredientDto {
        return IngredientDto(
            id = model.id,
            name = model.name,
            image = model.image,
        )
    }
}