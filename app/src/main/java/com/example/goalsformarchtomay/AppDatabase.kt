package com.example.goalsformarchtomay

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.goalsformarchtomay.dao.RecipeDao
import com.example.goalsformarchtomay.data.Recipe

@Database(entities = [Recipe::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun RecipeDao(): RecipeDao
}
