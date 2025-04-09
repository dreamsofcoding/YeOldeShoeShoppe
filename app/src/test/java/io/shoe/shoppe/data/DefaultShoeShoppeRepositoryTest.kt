package io.shoe.shoppe.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import io.shoe.shoppe.data.local.database.ShoeShoppe
import io.shoe.shoppe.data.local.database.ShoeShoppeDao

/**
 * Unit tests for [DefaultShoeShoppeRepository].
 */
@OptIn(ExperimentalCoroutinesApi::class) // TODO: Remove when stable
class DefaultShoeShoppeRepositoryTest {

    @Test
    fun shoeShoppes_newItemSaved_itemIsReturned() = runTest {
        val repository = DefaultShoeShoppeRepository(FakeShoeShoppeDao())

        repository.add("Repository")

        assertEquals(repository.shoeShoppes.first().size, 1)
    }

}

private class FakeShoeShoppeDao : ShoeShoppeDao {

    private val data = mutableListOf<ShoeShoppe>()

    override fun getShoeShoppes(): Flow<List<ShoeShoppe>> = flow {
        emit(data)
    }

    override suspend fun insertShoeShoppe(item: ShoeShoppe) {
        data.add(0, item)
    }
}
