package ru.test.app.picsum.persist

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.test.app.picsum.persist.model.PicsEntity

@Database(entities = [PicsEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun picsDao(): PicsDao
}
