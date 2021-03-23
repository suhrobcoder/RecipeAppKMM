package uz.suhrob.recipeappkmm.androidApp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.suhrob.recipeappkmm.shared.data.database.dao.InstructionsDao
import uz.suhrob.recipeappkmm.shared.data.database.dao.RecipeDao
import uz.suhrob.recipeappkmm.shared.data.network.RecipeApi
import uz.suhrob.recipeappkmm.shared.data.repository.RecipeRepositoryImpl
import uz.suhrob.recipeappkmm.shared.data.repository.SavedRecipesRepositoryImpl
import uz.suhrob.recipeappkmm.shared.data.settings.SettingsFactory
import uz.suhrob.recipeappkmm.shared.domain.repository.RecipeRepository
import uz.suhrob.recipeappkmm.shared.domain.repository.RecipeSettingsRepository
import uz.suhrob.recipeappkmm.shared.domain.repository.SavedRecipesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRecipeRepository(
        recipeApi: RecipeApi,
    ): RecipeRepository = RecipeRepositoryImpl(recipeApi)

    @Provides
    @Singleton
    fun provideRecipeSettingsRepository(
        @ApplicationContext context: Context,
    ): RecipeSettingsRepository = SettingsFactory(context).createSettings()

    @Provides
    @Singleton
    fun provideSavedRecipesRepository(
        recipeDao: RecipeDao,
        instructionsDao: InstructionsDao,
    ): SavedRecipesRepository = SavedRecipesRepositoryImpl(recipeDao, instructionsDao)
}