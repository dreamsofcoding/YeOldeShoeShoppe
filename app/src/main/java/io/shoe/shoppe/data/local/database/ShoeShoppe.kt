package io.shoe.shoppe.data.local.database

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity
data class ShoeShoppe(
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}

@Dao
interface ShoeShoppeDao {
    @Query("SELECT * FROM shoeshoppe ORDER BY uid DESC LIMIT 10")
    fun getShoeShoppes(): Flow<List<ShoeShoppe>>

    @Insert
    suspend fun insertShoeShoppe(item: ShoeShoppe)
}
