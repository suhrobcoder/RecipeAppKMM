package uz.suhrob.recipeappkmm.shared.data.repository

import kotlinx.coroutines.flow.Flow
import uz.suhrob.recipeappkmm.shared.data.database.dao.InstructionsDao
import uz.suhrob.recipeappkmm.shared.data.database.dao.RecipeDao
import uz.suhrob.recipeappkmm.shared.data.database.model.RecipeEntity
import uz.suhrob.recipeappkmm.shared.domain.repository.SavedRecipesRepository

class SavedRecipesRepositoryImpl(
    private val recipeDao: RecipeDao,
    private val instructionsDao: InstructionsDao,
) : SavedRecipesRepository {
    override fun insertRecipe(recipe: RecipeEntity) {
        recipeDao.insertRecipe(recipe)
        instructionsDao.insertInstructions(recipe.instructionSteps, recipe.id)
    }

    override fun deleteRecipe(id: Int) {
        recipeDao.deleteRecipe(id.toLong())
        instructionsDao.deleteInstructionsByRecipeId(id.toLong())
    }

    override fun getAllRecipes(): Flow<List<RecipeEntity>> {
        return recipeDao.getAllRecipes()
    }

    override fun getRecipeById(id: Int): RecipeEntity {
        val instructions = instructionsDao.getInstructionStepsByRecipeId(id.toLong())
        return recipeDao.getRecipeById(id.toLong()).copy(instructionSteps = instructions)
    }
}