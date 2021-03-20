package uz.suhrob.recipeappkmm.shared.data.network.mapper

import uz.suhrob.recipeappkmm.shared.data.network.model.RecipeDto
import uz.suhrob.recipeappkmm.shared.domain.model.Recipe
import uz.suhrob.recipeappkmm.shared.domain.util.BaseMapper

class RecipeDtoMapper(
    private val instructionStepMapper: InstructionStepDtoMapper
) : BaseMapper<RecipeDto, Recipe> {
    override fun toDomain(model: RecipeDto): Recipe {
        return Recipe(
            id = model.id,
            title = model.title,
            summary = model.summary,
            diets = model.diets,
            readyInMinutes = model.readyInMinutes,
            instructions = model.instructions,
            image = model.image,
            vegetarian = model.vegetarian,
            instructionSteps = instructionStepMapper.toDomainList(if (model.analyzedInstructions.isNotEmpty()) model.analyzedInstructions[0].steps else listOf()),
        )
    }

    override fun fromDomain(model: Recipe): RecipeDto {
        return RecipeDto(
            id = model.id,
            title = model.title,
            summary = model.summary,
            diets = model.diets,
            readyInMinutes = model.readyInMinutes,
            instructions = model.instructions,
            image = model.image,
            vegetarian = model.vegetarian,
            analyzedInstructions = listOf(),
        )
    }
}