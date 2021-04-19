package uz.suhrob.recipeappkmm.androidApp.presentation.home.recipes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.google.accompanist.insets.statusBarsHeight
import kotlinx.coroutines.flow.collect
import uz.suhrob.recipeappkmm.androidApp.presentation.home.components.RecipeItem
import uz.suhrob.recipeappkmm.androidApp.presentation.navigation.Navigation
import uz.suhrob.recipeappkmm.shared.domain.model.Recipe

@ExperimentalFoundationApi
@Composable
fun RecipesScreen(mainNavController: NavController, viewModel: RecipesViewModel) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = null) {
        viewModel.effect.collect {
            when (it) {
                is RecipesEffect.NavigateDetailsScreen -> {
                    mainNavController.navigate("${Navigation.Details.route}/${it.recipeId}")
                }
            }
        }
    }

    Column(modifier = Modifier.padding(bottom = 56.dp)) {
        RecipesScreenAppbar()

        BoxWithConstraints {
            LazyRecipeGridList(
                state = rememberLazyListState(),
                maxWidth = maxWidth,
                minSize = 180.dp,
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                items = state.recipes,
                loading = state.loading,
                error = state.error != null,
            ) { event ->
                viewModel.setEvent(event)
            }
        }
    }
}

@Composable
fun RecipesScreenAppbar() {
    Surface(
        color = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        elevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Spacer(modifier = Modifier.statusBarsHeight())
            Row(modifier = Modifier.height(56.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Recipes", style = MaterialTheme.typography.h5)
            }
        }
    }
}

@Composable
fun LazyRecipeGridList(
    modifier: Modifier = Modifier,
    state: LazyListState,
    maxWidth: Dp,
    minSize: Dp,
    contentPadding: PaddingValues,
    items: List<Recipe>,
    loading: Boolean,
    error: Boolean,
    setEvent: (RecipesEvent) -> Unit,
) {
    val nColumns = maxOf((maxWidth / minSize).toInt(), 1)

    LazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding
    ) {
        val rowItems = items.chunked(nColumns)
        itemsIndexed(rowItems) { index, rowItem ->
            Row {
                for (columnIndex in 0 until nColumns) {
                    val itemIndex = index * nColumns + columnIndex
                    if (itemIndex < items.size) {
                        val recipe = rowItem[columnIndex]

                        Box(
                            modifier = Modifier.weight(1f, fill = true),
                            propagateMinConstraints = true
                        ) {
                            if (itemIndex + 1 == items.size && !loading && !error) {
                                setEvent(RecipesEvent.LoadNextPage)
                            }

                            RecipeItem(
                                recipe = recipe,
                                modifier = Modifier.padding(4.dp),
                            ) {
                                setEvent(RecipesEvent.OnItemClick(recipe.id))
                            }
                        }
                    } else {
                        Spacer(Modifier.weight(1f, fill = true))
                    }
                }
            }
        }

        if (loading) {
            item {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }
            }
        }

        if (error) {
            item {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Button(
                        onClick = { setEvent(RecipesEvent.Retry) },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Retry")
                    }
                }
            }
        }
    }
}
