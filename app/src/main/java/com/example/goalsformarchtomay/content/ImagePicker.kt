package com.example.goalsformarchtomay.content

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ImagePicker(
    imageUrl: String,
    onImageSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // ユーザーがギャラリーから画像を選択肢、その画像URLをViewModelのimageUrlに変換する
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) { uri: Uri? ->
        imageUri = uri
        onImageSelected(uri?.toString() ?: "")
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { imagePickerLauncher.launch("image/*") },
        contentAlignment = Alignment.CenterStart
    ) {
        // 既存の画像を表示し、新しい画像を選択した際に画像を更新する
        val painter = rememberAsyncImagePainter(model = imageUri ?: imageUrl)
        if (imageUri != null || imageUrl.isNotEmpty()) {
            Image(
                painter = painter,
                contentDescription = "画像Box",
                modifier = Modifier.size(100.dp)
            )
        } else {
            Text(
                text = "画像を選択",
                color = Color.Blue
            )
        }
    }
}
