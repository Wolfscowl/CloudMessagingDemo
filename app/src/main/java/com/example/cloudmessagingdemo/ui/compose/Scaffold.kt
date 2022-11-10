package com.example.cloudmessagingdemo.ui.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BasicScaffold(
    title: String,
    content: @Composable () -> Unit = {}
) {
    val materialBlue700= Color(0xFF1976D2)
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(title = { Text(title) },backgroundColor = materialBlue700)  },
        content = { content() },
        bottomBar = { BottomAppBar(backgroundColor = materialBlue700) {  } }
    )
}