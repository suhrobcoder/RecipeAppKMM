package uz.suhrob.recipeappkmm.shared.data.database.mapper

import uz.suhrob.recipeappkmm.shared.data.database.model.RecipeEntity
import uz.suhrob.recipeappkmm.shared.domain.model.Recipe
import uz.suhrob.recipeappkmm.shared.domain.util.BaseMapper

class RecipeEntityMapper : BaseMapper<RecipeEntity, Recipe> {
    override fun toDomain(model: RecipeEntity): Recipe {
        return Recipe(
            id = model.id.toInt(),
            title = model.title,
            summary = model.summary,
            diets = model.diets.split("|"),
            readyInMinutes = model.readyInMinutes?.toInt(),
            instructions = model.instructions,
            image = model.image,
            vegetarian = model.vegetarian == 1L,
            cuisines = model.cuisines.split("|"),
            instructionSteps = listOf(),
        )
    }

    override fun fromDomain(model: Recipe): RecipeEntity {
        return RecipeEntity(
            id = model.id.toLong(),
            title = model.title,
            summary = model.summary,
            diets = model.diets.joinToString(separator = "|"),
            readyInMinutes = model.readyInMinutes?.toLong(),
            instructions = model.instructions,
            image = model.image,
            vegetarian = if (model.vegetarian) 1 else 0,
            cuisines = model.cuisines.joinToString(separator = "|"),
        )
    }
}