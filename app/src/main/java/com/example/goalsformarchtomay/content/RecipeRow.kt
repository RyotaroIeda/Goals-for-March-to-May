package com.example.goalsformarchtomay.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.goalsformarchtomay.data.Recipe
import com.example.goalsformarchtomay.R

@Composable
fun RecipeRow(
    recipe: Recipe,
    onClickRow: (Recipe) -> Unit,
    onClickDelete: (Recipe) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    onClickRow(recipe)
                }
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val painter = rememberImagePainter(
                data = recipe.imageUrl ?: "",
                builder = {
                    // ローディング中やエラー時に表示するデフォルトの画像を設定
                    placeholder(R.drawable.ic_launcher_background)
                    error(R.drawable.ic_launcher_background)
                }
            )
            Image(
                painter = painter,
                contentDescription = "アイコン",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = recipe.title)
            Spacer(modifier = Modifier.weight(1f))
            Row {
                IconButton(onClick = {
                    onClickRow(recipe)
                }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "更新",
                        tint = Color.Green
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                IconButton(onClick = {
                    onClickDelete(recipe)
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "削除",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RecipeRowPreview() {
    RecipeRow(
        recipe = Recipe(title = "preview", description = "description"),
        onClickRow = {},
        onClickDelete = {}
    )
}
