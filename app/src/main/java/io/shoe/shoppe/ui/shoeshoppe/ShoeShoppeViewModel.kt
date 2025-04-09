package io.shoe.shoppe.ui.shoeshoppe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import io.shoe.shoppe.data.ShoeShoppeRepository
import io.shoe.shoppe.ui.shoeshoppe.ShoeShoppeUiState.Error
import io.shoe.shoppe.ui.shoeshoppe.ShoeShoppeUiState.Loading
import io.shoe.shoppe.ui.shoeshoppe.ShoeShoppeUiState.Success
import javax.inject.Inject

@HiltViewModel
class ShoeShoppeViewModel @Inject constructor(
    private val shoeShoppeRepository: ShoeShoppeRepository
) : ViewModel() {

    val uiState: StateFlow<ShoeShoppeUiState> = shoeShoppeRepository
        .shoeShoppes.map<List<String>, ShoeShoppeUiState>(::Success)
        .catch { emit(Error(it)) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    fun addShoeShoppe(name: String) {
        viewModelScope.launch {
            shoeShoppeRepository.add(name)
        }
    }
}

sealed interface ShoeShoppeUiState {
    object Loading : ShoeShoppeUiState
    data class Error(val throwable: Throwable) : ShoeShoppeUiState
    data class Success(val data: List<String>) : ShoeShoppeUiState
}
