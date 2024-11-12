package com.example.goalsformarchtomay.dao

import androidx.room.*
import com.example.goalsformarchtomay.data.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Insert
    suspend fun insertRecipe(recipe: Recipe)

    @Query("SELECT * FROM Recipe")
    fun loadAllRecipes(): Flow<List<Recipe>>

    @Update
    suspend fun updateRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)
}
