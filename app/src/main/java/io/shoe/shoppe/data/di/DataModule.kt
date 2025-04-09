package io.shoe.shoppe.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import io.shoe.shoppe.data.ShoeShoppeRepository
import io.shoe.shoppe.data.DefaultShoeShoppeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindsShoeShoppeRepository(
        shoeShoppeRepository: DefaultShoeShoppeRepository
    ): ShoeShoppeRepository
}

class FakeShoeShoppeRepository @Inject constructor() : ShoeShoppeRepository {
    override val shoeShoppes: Flow<List<String>> = flowOf(fakeShoeShoppes)

    override suspend fun add(name: String) {
        throw NotImplementedError()
    }
}

val fakeShoeShoppes = listOf("One", "Two", "Three")
