package com.example.android_lab

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(question: Question)

    @Query("SELECT * FROM questions")
    fun getAll(): List<Question>

    @Query("DELETE FROM questions")
    fun clearAll()
}
