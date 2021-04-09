package uz.suhrob.recipeappkmm.androidApp.presentation.onboarding

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.suhrob.recipeappkmm.androidApp.R
import uz.suhrob.recipeappkmm.androidApp.presentation.navigation.Navigation
import uz.suhrob.recipeappkmm.androidApp.presentation.ui.Gray400
import uz.suhrob.recipeappkmm.androidApp.presentation.ui.Gray900

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalAnimationApi
@Composable
fun OnboardingScreen(viewModel: OnboardingViewModel, navController: NavController) {
    val state by viewModel.uiState.collectAsState()
    val composeScope = rememberCoroutineScope()
    composeScope.launch {
        viewModel.effect.collect { effect ->
            when (effect) {
                is OnboardingEffect.NavigateHomePage -> navController.navigate(Navigation.Home.route) {
                    popUpTo(
                        navController.graph.startDestination
                    ) { inclusive = true }
                }
            }
        }
    }
    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            OnboardingAppBar(
                stepCount = steps.size,
                onSkipClick = { viewModel.setEvent(OnboardingEvent.Skip) },
                step = state.step
            )
        }
    ) {
        Column {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                OnboardingBody(
                    title = state.title,
                    items = state.items,
                    selectedItems = state.selectedItems,
                    onSelect = { item -> viewModel.setEvent(OnboardingEvent.ItemSelected(item)) },
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            BottomBar(
                isPrevVisible = state.step != STARTING_STEP,
                isLastStep = state.isLastStep,
                onPrevClick = { viewModel.setEvent(OnboardingEvent.PreviousStep) },
                onNextClick = { viewModel.setEvent(OnboardingEvent.NextStep) },
            )
        }
    }
}

@Composable
fun OnboardingAppBar(stepCount: Int, step: Int, onSkipClick: () -> Unit) {
    TopAppBar(
        backgroundColor = Color.White,
        elevation = 0.dp,
    ) {
        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            for (i in 1..stepCount) {
                StepItem(
                    step = i,
                    current = i == step,
                    modifier = Modifier.padding(
                        start = if (i == 1) 0.dp else 4.dp,
                        end = if (i == stepCount) 0.dp else 4.dp
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick = onSkipClick) {
                Text(stringResource(R.string.skip), fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun OnboardingBody(
    title: String,
    items: List<String>,
    selectedItems: List<String>,
    onSelect: (String) -> Unit,
) {
    Text(title, style = MaterialTheme.typography.h4)
    Text(
        stringResource(R.string.onboarding_body),
        style = MaterialTheme.typography.subtitle1
    )
    Spacer(modifier = Modifier.height(16.dp))
    FlowRow(
        mainAxisSpacing = 8.dp,
        crossAxisSpacing = 8.dp,
    ) {
        items.forEach { item ->
            RecipeChip(text = item, selected = selectedItems.contains(item)) {
                onSelect(item)
            }
        }
    }
}

@Composable
fun StepItem(modifier: Modifier = Modifier, step: Int, current: Boolean = false) {
    val color by animateColorAsState(targetValue = if (current) Gray900 else Gray400)
    Box(
        modifier = modifier
            .size(size = 32.dp)
            .border(
                width = 1.dp,
                color = color,
                shape = RoundedCornerShape(percent = 50)
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = step.toString(), color = color, fontSize = 16.sp)
    }
}

@ExperimentalAnimationApi
@Composable
fun BottomBar(
    isPrevVisible: Boolean = false,
    isLastStep: Boolean,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit
) {
    val animSpec = TweenSpec<Float>(durationMillis = 300)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        val weight by animateFloatAsState(
            targetValue = if (isPrevVisible) 1f else 0.001f,
            animationSpec = animSpec
        )
        val spacerWeight by animateFloatAsState(
            targetValue = if (isPrevVisible) 0.3f else 0.001f,
            animationSpec = animSpec
        )
        PreviousButton(
            onClick = onPrevClick,
            isVisible = isPrevVisible,
            modifier = Modifier.weight(weight)
        )
        Spacer(modifier = Modifier.weight(spacerWeight))
        NextButton(onClick = onNextClick, isLastStep = isLastStep, modifier = Modifier.weight(1f))
    }
}

@Composable
fun NextButton(modifier: Modifier = Modifier, isLastStep: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier,
        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp)
    ) {
        Text(
            text = stringResource(if (isLastStep) R.string.get_started else R.string.next_step),
            fontSize = 18.sp,
            maxLines = 1
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            imageVector = Icons.Filled.ArrowForward,
            modifier = Modifier.size(24.dp),
            contentDescription = null
        )
    }
}

@Composable
fun PreviousButton(modifier: Modifier = Modifier, isVisible: Boolean, onClick: () -> Unit) {
    val color by animateColorAsState(targetValue = if (isVisible) Gray400 else Color.Transparent)
    OutlinedButton(onClick = onClick, modifier = modifier, border = BorderStroke(1.dp, color)) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            modifier = Modifier.size(24.dp),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = stringResource(R.string.previous), fontSize = 18.sp, maxLines = 1)
    }
}

@Composable
fun RecipeChip(text: String, selected: Boolean, onClick: () -> Unit) {
    val backgroundColor by animateColorAsState(targetValue = if (selected) MaterialTheme.colors.primary else Color.Transparent)
    val textColor by animateColorAsState(targetValue = if (selected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.primaryVariant)
    Box(modifier = Modifier
        .border(
            width = if (selected) 0.dp else 1.dp,
            color = MaterialTheme.colors.secondaryVariant,
            shape = CircleShape
        )
        .clip(CircleShape)
        .background(color = backgroundColor)
        .clickable { onClick() }
        .padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(text = text, color = textColor, fontSize = 18.sp)
    }
}