package com.tsybulnik.intelliastest.data.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.tsybulnik.intelliastest.data.model.WordItemDto

interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWordItem(wordItem: WordItemDto)
}