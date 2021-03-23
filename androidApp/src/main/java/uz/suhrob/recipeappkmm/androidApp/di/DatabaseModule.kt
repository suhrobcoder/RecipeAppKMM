package uz.suhrob.recipeappkmm.androidApp.di

import android.content.Context
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.suhrob.recipeappkmm.database.RecipeDb
import uz.suhrob.recipeappkmm.shared.data.database.DatabaseDriverFactory
import uz.suhrob.recipeappkmm.shared.data.database.dao.InstructionsDao
import uz.suhrob.recipeappkmm.shared.data.database.dao.RecipeDao
import uz.suhrob.recipeappkmm.shared.data.database.mapper.InstructionStepEntityMapper
import uz.suhrob.recipeappkmm.shared.data.database.mapper.RecipeEntityMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideSqlDriver(
        @ApplicationContext context: Context,
    ): SqlDriver = DatabaseDriverFactory(context).createDriver()

    @Provides
    @Singleton
    fun provideRecipeDb(driver: SqlDriver): RecipeDb = RecipeDb(driver)

    @Provides
    @Singleton
    fun provideRecipeDao(database: RecipeDb): RecipeDao = RecipeDao(database)

    @Provides
    @Singleton
    fun provideInstructionsDao(database: RecipeDb): InstructionsDao = InstructionsDao(database)

    @Provides
    @Singleton
    fun provideInstructionEntityMapper(): InstructionStepEntityMapper = InstructionStepEntityMapper()

    @Provides
    @Singleton
    fun provideRecipeEntityMapper(
        instructionMapper: InstructionStepEntityMapper,
    ): RecipeEntityMapper = RecipeEntityMapper(instructionMapper)
}