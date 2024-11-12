package com.example.goalsformarchtomay.content

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goalsformarchtomay.viewModel.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainContent(viewModel: MainViewModel = hiltViewModel()) {
    if (viewModel.isShowRecipeCreateDialog) {
        CreateDialog()
    }
    if (viewModel.isShowRecipeEditDialog) {
        EditDialog()
    }
    Scaffold(
        topBar = {
            Header()
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.isShowRecipeCreateDialog = true
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "新規作成")
            }
        })
    {
        //　状態の変化があった場合のみ再描画
        val recipes by viewModel.recipes.collectAsState(initial = emptyList())
        RecipeList(
            recipes = recipes,
            onClickRow = {
                viewModel.setEditingRecipe(it)
                viewModel.isShowRecipeEditDialog = true
            },
            onClickDelete = {
                viewModel.deleteRecipe(it)
            }
        )
    }
}
