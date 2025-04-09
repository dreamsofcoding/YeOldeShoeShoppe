package io.shoe.shoppe.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import io.shoe.shoppe.data.local.database.ShoeShoppe
import io.shoe.shoppe.data.local.database.ShoeShoppeDao
import javax.inject.Inject

interface ShoeShoppeRepository {
    val shoeShoppes: Flow<List<String>>

    suspend fun add(name: String)
}

class DefaultShoeShoppeRepository @Inject constructor(
    private val shoeShoppeDao: ShoeShoppeDao
) : ShoeShoppeRepository {

    override val shoeShoppes: Flow<List<String>> =
        shoeShoppeDao.getShoeShoppes().map { items -> items.map { it.name } }

    override suspend fun add(name: String) {
        shoeShoppeDao.insertShoeShoppe(ShoeShoppe(name = name))
    }
}
