package uz.suhrob.recipeappkmm.shared.data.database

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import uz.suhrob.recipeappkmm.database.RecipeDb

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(RecipeDb.Schema, context, "recipe.db")
    }
}