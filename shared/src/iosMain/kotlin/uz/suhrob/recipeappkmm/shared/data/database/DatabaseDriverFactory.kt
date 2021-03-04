package uz.suhrob.recipeappkmm.shared.data.database

import com.squareup.sqldelight.db.SqlDriver
import uz.suhrob.recipeappkmm.database.RecipeDb

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(RecipeDb.Schema, "recipe.db")
    }
}