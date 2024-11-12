package com.example.goalsformarchtomay.content

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Header() {
    TopAppBar(
        title = {
            Text(
                text = "Share Recipes",
                style = MaterialTheme.typography.h6,
                color = Color.White
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        elevation = 8.dp,
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.MoreVert, contentDescription = "More options")
            }
        }
    )
}
