package io.shoe.shoppe.testdi

import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.shoe.shoppe.data.ShoeShoppeRepository
import io.shoe.shoppe.data.di.DataModule
import io.shoe.shoppe.data.di.FakeShoeShoppeRepository

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class]
)
interface FakeDataModule {

    @Binds
    abstract fun bindRepository(
        fakeRepository: FakeShoeShoppeRepository
    ): ShoeShoppeRepository
}
