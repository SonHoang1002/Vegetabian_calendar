package com.hts.vegetabiancalendar.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import com.hts.vegetabiancalendar.view_model.MyViewModel

@Composable
fun ProfileScreen(viewModel: MyViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = "Navigation without arguments",
                Modifier.padding(10.dp),
                color = Color.Black
            )
            Text(text = "Profile Screen", Modifier.padding(10.dp), color = Color.Black)
        }
    }
}