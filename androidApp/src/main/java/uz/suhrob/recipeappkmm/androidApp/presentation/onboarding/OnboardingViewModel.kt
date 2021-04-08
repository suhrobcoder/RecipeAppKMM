package uz.suhrob.recipeappkmm.androidApp.presentation.onboarding

import dagger.hilt.android.lifecycle.HiltViewModel
import uz.suhrob.recipeappkmm.androidApp.presentation.base.*
import uz.suhrob.recipeappkmm.shared.domain.interactor.SetCuisines
import uz.suhrob.recipeappkmm.shared.domain.interactor.SetDiets
import uz.suhrob.recipeappkmm.shared.presentation.onboarding.onboardingSteps
import javax.inject.Inject

const val STARTING_STEP = 1
val steps = onboardingSteps

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val setCuisines: SetCuisines,
    private val setDiets: SetDiets,
) : BaseViewModel<OnboardingEvent, OnboardingState, OnboardingEffect>(
    OnboardingState(
        step = STARTING_STEP,
        title = steps[STARTING_STEP-1].title,
        items = steps[STARTING_STEP-1].items,
        selectedItems = listOf()
    )
) {
    private var currentStep = STARTING_STEP
    private val selectedDiets = ArrayList<String>()
    private val selectedCuisines = ArrayList<String>()

    override fun handleEvent(event: OnboardingEvent) {
        when (event) {
            is OnboardingEvent.NextStep -> nextStep()
            is OnboardingEvent.PreviousStep -> prevStep()
            is OnboardingEvent.Skip -> setEffect { OnboardingEffect.NavigateHomePage }
            is OnboardingEvent.ItemSelected -> itemSelected(event.item)
        }
    }

    private fun nextStep() {
        if (currentStep == steps.size) {
            save()
            setEffect { OnboardingEffect.NavigateHomePage }
        } else if (currentStep < steps.size) {
            currentStep++
            setState {
                val selectedItems = when (currentStep) {
                    1 -> selectedDiets
                    2 -> selectedCuisines
                    else -> listOf()
                }
                OnboardingState(
                    step = currentStep,
                    title = steps[currentStep-1].title,
                    items = steps[currentStep-1].items,
                    selectedItems = selectedItems,
                    isLastStep = currentStep == steps.size,
                )
            }
        }
    }

    private fun prevStep() {
        if (currentStep > STARTING_STEP) {
            currentStep--
            setState {
                val selectedItems = when (currentStep) {
                    1 -> selectedDiets
                    2 -> selectedCuisines
                    else -> listOf()
                }
                OnboardingState(
                    step = currentStep,
                    title = steps[currentStep-1].title,
                    items = steps[currentStep-1].items,
                    selectedItems = selectedItems,
                    isLastStep = currentStep == steps.size,
                )
            }
        }
    }

    private fun save() {
        setCuisines(selectedCuisines)
        setDiets(selectedDiets)
    }

    private fun itemSelected(item: String) {
        when (currentStep) {
            1 -> if (selectedDiets.contains(item)) {
                selectedDiets.remove(item)
            } else {
                selectedDiets.add(item)
            }
            2 -> if (selectedCuisines.contains(item)) {
                selectedCuisines.remove(item)
            } else {
                selectedCuisines.add(item)
            }
        }
        setState {
            val selectedItems = when (currentStep) {
                1 -> selectedDiets
                2 -> selectedCuisines
                else -> listOf()
            }
            copy(
                selectedItems = selectedItems,
            )
        }
    }
}

data class OnboardingState(
    val step: Int,
    val title: String,
    val items: List<String>,
    val selectedItems: List<String>,
    val isLastStep: Boolean = false,
) : IState {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = step
        result = 31 * result + title.hashCode()
        result = 31 * result + items.hashCode()
        result = 31 * result + selectedItems.hashCode()
        result = 31 * result + isLastStep.hashCode()
        return result
    }
}

sealed class OnboardingEvent : IEvent {
    object NextStep : OnboardingEvent()
    object PreviousStep : OnboardingEvent()
    object Skip : OnboardingEvent()
    data class ItemSelected(val item: String) : OnboardingEvent()
}

sealed class OnboardingEffect : IEffect {
    object NavigateHomePage : OnboardingEffect()
}