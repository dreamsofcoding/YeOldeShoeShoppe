package io.shoe.shoppe.ui.shoeshoppe


import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import io.shoe.shoppe.data.ShoeShoppeRepository

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class) // TODO: Remove when stable
class ShoeShoppeViewModelTest {
    @Test
    fun uiState_initiallyLoading() = runTest {
        val viewModel = ShoeShoppeViewModel(FakeShoeShoppeRepository())
        assertEquals(viewModel.uiState.first(), ShoeShoppeUiState.Loading)
    }

    @Test
    fun uiState_onItemSaved_isDisplayed() = runTest {
        val viewModel = ShoeShoppeViewModel(FakeShoeShoppeRepository())
        assertEquals(viewModel.uiState.first(), ShoeShoppeUiState.Loading)
    }
}

private class FakeShoeShoppeRepository : ShoeShoppeRepository {

    private val data = mutableListOf<String>()

    override val shoeShoppes: Flow<List<String>>
        get() = flow { emit(data.toList()) }

    override suspend fun add(name: String) {
        data.add(0, name)
    }
}
