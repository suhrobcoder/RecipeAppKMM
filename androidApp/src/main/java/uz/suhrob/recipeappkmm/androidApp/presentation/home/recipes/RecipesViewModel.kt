package uz.suhrob.recipeappkmm.androidApp.presentation.home.recipes

import dagger.hilt.android.lifecycle.HiltViewModel
import uz.suhrob.recipeappkmm.androidApp.presentation.base.BaseViewModel
import uz.suhrob.recipeappkmm.androidApp.presentation.base.IEffect
import uz.suhrob.recipeappkmm.androidApp.presentation.base.IEvent
import uz.suhrob.recipeappkmm.androidApp.presentation.base.IState
import uz.suhrob.recipeappkmm.shared.data.network.LastPageReachedException
import uz.suhrob.recipeappkmm.shared.domain.interactor.GetCuisines
import uz.suhrob.recipeappkmm.shared.domain.interactor.GetDiets
import uz.suhrob.recipeappkmm.shared.domain.interactor.GetRandomRecipes
import uz.suhrob.recipeappkmm.shared.domain.model.Recipe
import javax.inject.Inject

private const val MAX_PAGE = 5

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRandomRecipes: GetRandomRecipes,
    private val getCuisines: GetCuisines,
    private val getDiets: GetDiets,
) : BaseViewModel<RecipesEvent, RecipesState, RecipesEffect>(RecipesState(listOf(), true, null)) {
    private var currentPage = 0
    private var tags = getCuisines() + getDiets()
    private var maxLoaded = false

    init {
        setEvent(RecipesEvent.LoadNextPage)
    }

    override suspend fun handleEvent(event: RecipesEvent) {
        when (event) {
            is RecipesEvent.LoadNextPage -> loadNextPage()
            is RecipesEvent.Retry -> loadNextPage()
            is RecipesEvent.Refresh -> refresh()
            is RecipesEvent.OnItemClick -> setEffect { RecipesEffect.NavigateDetailsScreen(event.id) }
        }
    }

    private suspend fun loadNextPage() {
        if (maxLoaded) return
        currentPage++
        if (currentPage > MAX_PAGE) return
        setState {
            copy(loading = true, error = null)
        }
        try {
            val newItems = getRandomRecipes(tags, currentPage)
            setState {
                copy(recipes = recipes + newItems, loading = false)
            }
        } catch (e: LastPageReachedException) {
            maxLoaded = true
            setState {
                copy(loading = false)
            }
        } catch (e: Exception) {
            setState {
                copy(loading = false, error = e.message)
            }
            currentPage--
        }
    }

    private suspend fun refresh() {
        currentPage = 0
        tags = getCuisines() + getDiets()
        maxLoaded = false
        loadNextPage()
    }
}

data class RecipesState(
    val recipes: List<Recipe>,
    val loading: Boolean,
    val error: String?,
) : IState {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = recipes.hashCode()
        result = 31 * result + loading.hashCode()
        result = 31 * result + (error?.hashCode() ?: 0)
        return result
    }
}

sealed class RecipesEvent : IEvent {
    object LoadNextPage : RecipesEvent()
    object Retry : RecipesEvent()
    object Refresh : RecipesEvent()
    data class OnItemClick(val id: Int) : RecipesEvent()
}

sealed class RecipesEffect : IEffect {
    data class NavigateDetailsScreen(val recipeId: Int) : RecipesEffect()
}
