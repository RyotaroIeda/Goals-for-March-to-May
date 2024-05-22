package com.example.goalsformarchtomay.content

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goalsformarchtomay.viewModel.MainViewModel

@Composable
fun EditDialog(viewModel: MainViewModel = hiltViewModel()) {
    val isTitleEmpty = viewModel.title.isEmpty()
    val isDescriptionEmpty = viewModel.description.isEmpty()

    AlertDialog(
        onDismissRequest = {
            viewModel.isShowRecipeEditDialog = false
            viewModel.resetProperties()
        },
        title = {
            Text(text = "レシピ更新")
        },
        text = {
            Column {
                Text(text = "タイトル")
                TextField(
                    value = viewModel.title,
                    onValueChange = {
                        viewModel.title = it
                    },
                    isError = isTitleEmpty
                )
                if (isTitleEmpty) {
                    Text(
                        text = "タイトルを入力してください",
                        style = MaterialTheme.typography.caption,
                        color = Color.Red
                    )
                }
                Text(text = "詳細")
                TextField(
                    value = viewModel.description,
                    onValueChange = {
                        viewModel.description = it
                    },
                    isError = isDescriptionEmpty
                )
                if (isDescriptionEmpty) {
                    Text(
                        text = "詳細を入力してください",
                        style = MaterialTheme.typography.caption,
                        color = Color.Red
                    )
                }
                Text(text = "画像")
                ImagePicker(
                    imageUrl = viewModel.imageUrl,
                    onImageSelected = { imageUrl ->
                        viewModel.imageUrl = imageUrl
                    },
                    modifier = Modifier
                )
            }
        },
        buttons = {
            Row(modifier = Modifier.padding(8.dp)) {
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier.width(120.dp),
                    onClick = {
                        viewModel.isShowRecipeEditDialog = false
                        viewModel.resetProperties()
                    }
                ) {
                    Text(text = "キャンセル")
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier.width(120.dp),
                    onClick = {
                        viewModel.isShowRecipeEditDialog = false
                        viewModel.updateRecipe()
                    },
                    enabled = !(isTitleEmpty || isDescriptionEmpty)
                ) {
                    Text(text = "OK")
                }
            }
        }
    )
}
