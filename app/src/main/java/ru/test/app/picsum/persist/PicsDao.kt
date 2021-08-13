package ru.test.app.picsum.persist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ru.test.app.picsum.persist.model.PicsEntity

@Dao
interface PicsDao {
  @Query("SELECT * FROM picsum WHERE 1 order by id DESC")
  fun getFavoritePics(): List<PicsEntity>

  @Insert(entity = PicsEntity::class, onConflict = REPLACE)
  fun saveItem(picsEntity: PicsEntity)

  @Delete(entity = PicsEntity::class)
  fun deleteItem(picsEntity: PicsEntity)
}
