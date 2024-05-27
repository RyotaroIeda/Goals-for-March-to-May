package com.example.goalsformarchtomay.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goalsformarchtomay.dao.RecipeDao
import com.example.goalsformarchtomay.data.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val recipeDao: RecipeDao) : ViewModel() {
    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var imageUrl by mutableStateOf("")
    var isShowRecipeEditDialog by mutableStateOf(false)
    var isShowRecipeCreateDialog by mutableStateOf(false)
    val recipes = recipeDao.loadAllRecipes().distinctUntilChanged()
    private var editRecipe: Recipe? = null
    val isEditing: Boolean
        get() = editRecipe != null

    fun createRecipe() {
        viewModelScope.launch {
            val newRecipe = Recipe(title = title, description = description, imageUrl = imageUrl)
            recipeDao.insertRecipe(newRecipe)
            resetProperties()
        }
    }

    fun setEditingRecipe(recipe: Recipe) {
        editRecipe = recipe
        title = recipe.title
        description = recipe.description
        imageUrl = recipe.imageUrl.toString()
    }

    fun updateRecipe() {
        editRecipe?.let { recipe ->
            viewModelScope.launch {
                recipe.title = title
                recipe.description = description
                recipe.imageUrl = imageUrl
                resetProperties()
            }
        }
    }

    fun deleteRecipe(recipe: Recipe) {
        viewModelScope.launch {
            recipeDao.deleteRecipe(recipe)
        }
    }

    fun resetProperties() {
        editRecipe = null
        title = ""
        description = ""
        imageUrl = ""
    }
}
