package com.example.a666.AfterLogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun LoadingImageBorder() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(195.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFf7f5f7),
            strokeWidth = 6.dp,
        )
//        Image(
////            painter = painterResource(),
//            contentDescription = "Profile Picture",
//            modifier = Modifier
//                .size(170.dp)
//                .clip(CircleShape),
//            contentScale = ContentScale.Crop
//        )
    }
}