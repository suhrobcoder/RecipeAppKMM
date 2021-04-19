package uz.suhrob.recipeappkmm.androidApp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<Event : IEvent, State : IState, Effect : IEffect>(initialState: State) : ViewModel() {
    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()


    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        subscribeEvents()
    }

    fun setEvent(event: Event) {
        val newEvent = event
        viewModelScope.launch { _event.emit(newEvent) }
    }

    protected fun setState(reduce: State.() -> State) {
        val newState = uiState.value.reduce()
        _uiState.value = newState
    }

    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    private fun subscribeEvents() {
        viewModelScope.launch {
            event.collect {
                handleEvent(it)
            }
        }
    }

    abstract suspend fun handleEvent(event: Event)
}