package uz.suhrob.recipeappkmm.shared.data.database.dao

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import uz.suhrob.recipeappkmm.database.RecipeDb
import uz.suhrob.recipeappkmm.shared.data.database.model.RecipeEntity

class RecipeDao(database: RecipeDb) {
    private val dbQueries = database.recipeDbQueries

    private val recipeMapper: (
        id: Long,
        title: String,
        summary: String,
        image: String?,
        vegetarian: Long,
        readyInMinutes: Long?,
        instructions: String?,
        cuisines: String,
        diets: String
    ) -> RecipeEntity =
        { id, title, summary, image, vegetarian, readyInMinutes, instructions, cuisines, diets ->
            RecipeEntity(
                id,
                title,
                summary,
                image,
                vegetarian,
                readyInMinutes,
                instructions,
                cuisines,
                diets
            )
        }

    fun insertRecipe(recipe: RecipeEntity) {
        dbQueries.insertRecipe(
            id = recipe.id,
            title = recipe.title,
            summary = recipe.summary,
            image = recipe.image,
            vegetarian = recipe.vegetarian,
            readyInMinutes = recipe.readyInMinutes,
            instructions = recipe.instructions,
            cuisines = recipe.cuisines,
            diets = recipe.diets,
        )
    }

    fun deleteRecipe(id: Long) {
        dbQueries.deleteRecipe(id = id)
    }

    fun getAllRecipes(): Flow<List<RecipeEntity>> {
        return dbQueries.getAllRecipes(mapper = recipeMapper).asFlow().mapToList()
    }

    fun getRecipeById(id: Long): RecipeEntity {
        return dbQueries.getRecipeById(id = id, mapper = recipeMapper).executeAsOne()
    }
}