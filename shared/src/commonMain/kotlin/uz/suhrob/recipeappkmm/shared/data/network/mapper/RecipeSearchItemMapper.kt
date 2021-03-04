package uz.suhrob.recipeappkmm.shared.data.network.mapper

import uz.suhrob.recipeappkmm.shared.data.network.model.RecipeSearchItemDto
import uz.suhrob.recipeappkmm.shared.domain.model.RecipeSearchItem
import uz.suhrob.recipeappkmm.shared.domain.util.BaseMapper

class RecipeSearchItemMapper : BaseMapper<RecipeSearchItemDto, RecipeSearchItem> {
    override fun toDomain(model: RecipeSearchItemDto): RecipeSearchItem {
        return RecipeSearchItem(
            id = model.id,
            title = model.title,
            image = model.image,
        )
    }

    override fun fromDomain(model: RecipeSearchItem): RecipeSearchItemDto {
        return RecipeSearchItemDto(
            id = model.id,
            title = model.title,
            image = model.image,
        )
    }
}