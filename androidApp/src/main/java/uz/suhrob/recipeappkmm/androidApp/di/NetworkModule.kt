package uz.suhrob.recipeappkmm.androidApp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.suhrob.recipeappkmm.shared.data.network.RecipeApi
import uz.suhrob.recipeappkmm.shared.data.network.mapper.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRecipeApi(): RecipeApi = RecipeApi()

    @Provides
    @Singleton
    fun provideEquipmentDtoMapper(): EquipmentDtoMapper = EquipmentDtoMapper()

    @Provides
    @Singleton
    fun provideIngredientMapper(): IngredientDtoMapper = IngredientDtoMapper()

    @Provides
    @Singleton
    fun provideInstructionDtoMapper(
        equipmentDtoMapper: EquipmentDtoMapper,
        ingredientDtoMapper: IngredientDtoMapper,
    ): InstructionStepDtoMapper = InstructionStepDtoMapper(equipmentDtoMapper, ingredientDtoMapper)

    @Provides
    @Singleton
    fun provideRecipeDtoMapper(
        instructionDtoMapper: InstructionStepDtoMapper,
    ): RecipeDtoMapper = RecipeDtoMapper(instructionDtoMapper)

    @Provides
    @Singleton
    fun provideSearchItemMapper(): RecipeSearchItemMapper = RecipeSearchItemMapper()
}