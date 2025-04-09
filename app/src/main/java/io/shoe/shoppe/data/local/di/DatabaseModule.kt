package io.shoe.shoppe.data.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.shoe.shoppe.data.local.database.AppDatabase
import io.shoe.shoppe.data.local.database.ShoeShoppeDao
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideShoeShoppeDao(appDatabase: AppDatabase): ShoeShoppeDao {
        return appDatabase.shoeShoppeDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "ShoeShoppe"
        ).build()
    }
}
