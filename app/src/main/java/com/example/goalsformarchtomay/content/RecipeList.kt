package com.example.goalsformarchtomay.content

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.goalsformarchtomay.data.Recipe

@Composable
fun RecipeList(
    recipes: List<Recipe>,
    onClickRow: (Recipe) -> Unit,
    onClickDelete: (Recipe) -> Unit,
) {
    LazyColumn {
        items(recipes) { recipe ->
            RecipeRow(
                recipe = recipe,
                onClickRow = onClickRow,
                onClickDelete = onClickDelete,
            )
        }
    }
}
