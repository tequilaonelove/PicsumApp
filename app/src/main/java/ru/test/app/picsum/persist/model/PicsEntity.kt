package ru.test.app.picsum.persist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "picsum")
data class PicsEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "width")
    val width: Int,
    @ColumnInfo(name = "height")
    val height: Int,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "download_url")
    val download_url: String,
    @ColumnInfo(name = "url")
    val url: String,
)