package uz.suhrob.recipeappkmm.androidApp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.suhrob.recipeappkmm.shared.data.database.mapper.RecipeEntityMapper
import uz.suhrob.recipeappkmm.shared.data.network.mapper.RecipeDtoMapper
import uz.suhrob.recipeappkmm.shared.data.network.mapper.RecipeSearchItemMapper
import uz.suhrob.recipeappkmm.shared.domain.interactor.*
import uz.suhrob.recipeappkmm.shared.domain.repository.RecipeRepository
import uz.suhrob.recipeappkmm.shared.domain.repository.RecipeSettingsRepository
import uz.suhrob.recipeappkmm.shared.domain.repository.SavedRecipesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    @Singleton
    fun provideAutoCompleteRecipeSearch(
        recipeRepository: RecipeRepository,
        recipeSearchItemMapper: RecipeSearchItemMapper,
    ) = AutoCompleteRecipeSearch(recipeRepository, recipeSearchItemMapper)

    @Provides
    @Singleton
    fun provideDeleteRecipe(
        savedRecipesRepository: SavedRecipesRepository,
    ) = DeleteRecipe(savedRecipesRepository)

    @Provides
    @Singleton
    fun provideGetCuisines(
        settingsRepository: RecipeSettingsRepository,
    ) = GetCuisines(settingsRepository)

    @Provides
    @Singleton
    fun provideGetDiets(
        settingsRepository: RecipeSettingsRepository,
    ) = GetDiets(settingsRepository)

    @Provides
    @Singleton
    fun provideGetRandomRecipes(
        recipeRepository: RecipeRepository,
        recipeDtoMapper: RecipeDtoMapper,
    ) = GetRandomRecipes(recipeRepository, recipeDtoMapper)

    @Provides
    @Singleton
    fun provideGetRecipeById(
        recipeRepository: RecipeRepository,
        savedRecipesRepository: SavedRecipesRepository,
        recipeDtoMapper: RecipeDtoMapper,
        recipeEntityMapper: RecipeEntityMapper,
    ) = GetRecipeById(recipeRepository, savedRecipesRepository, recipeDtoMapper, recipeEntityMapper)

    @Provides
    @Singleton
    fun provideGetSavedRecipes(
        savedRecipesRepository: SavedRecipesRepository,
        recipeEntityMapper: RecipeEntityMapper,
    ) = GetSavedRecipes(savedRecipesRepository, recipeEntityMapper)

    @Provides
    @Singleton
    fun provideInsertRecipe(
        savedRecipesRepository: SavedRecipesRepository,
        recipeEntityMapper: RecipeEntityMapper,
    ) = InsertRecipe(savedRecipesRepository, recipeEntityMapper)

    @Provides
    @Singleton
    fun provideSearchRecipes(
        recipeRepository: RecipeRepository,
        recipeSearchItemMapper: RecipeSearchItemMapper,
    ) = SearchRecipes(recipeRepository, recipeSearchItemMapper)

    @Provides
    @Singleton
    fun provideSetCuisines(
        settingsRepository: RecipeSettingsRepository,
    ) = SetCuisines(settingsRepository)

    @Provides
    @Singleton
    fun provideSetDiets(
        settingsRepository: RecipeSettingsRepository,
    ) = SetDiets(settingsRepository)

    @Provides
    @Singleton
    fun provideIsFirstRun(
        settingsRepository: RecipeSettingsRepository
    ) = IsFirstRun(settingsRepository)
}