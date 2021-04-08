package uz.suhrob.recipeappkmm.shared.presentation.onboarding

import uz.suhrob.recipeappkmm.shared.domain.util.cuisines
import uz.suhrob.recipeappkmm.shared.domain.util.diets

data class OnboardingStep(
    val title: String,
    val items: List<String>,
)

val onboardingSteps = listOf(
    OnboardingStep("Do you follow any of these diets?", items = diets),
    OnboardingStep("Your favorite cuisines?", items = cuisines),
)