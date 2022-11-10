package com.example.cloudmessagingdemo.ui.layout

import android.text.style.UnderlineSpan
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cloudmessagingdemo.ui.compose.BasicScaffold


@Composable
fun MainScreen(
    token: String,
    subs: List<String>,
    onClick: (String) -> Unit,
    content: @Composable () -> Unit
){
   BasicScaffold(title = "Cloud Messaging Demo") {
       var textFieldState by remember { mutableStateOf("") }

       Column(modifier = Modifier.fillMaxSize().padding(all = 20.dp),
           verticalArrangement = Arrangement.Top,
           horizontalAlignment = Alignment.CenterHorizontally,
       )

       {
           Text("   FCM Token   ", modifier = Modifier.padding(top = 40.dp, bottom = 10.dp)
               ,style = TextStyle(
                   color = Color.Black,
                   fontSize = 20.sp,
                   fontWeight = FontWeight.Bold)
           )
           Text(token + "")
           Text("   Subscription   ", modifier = Modifier.padding(top = 20.dp,bottom = 10.dp)
               ,style = TextStyle(
                   color = Color.Black,
                   fontSize = 20.sp,
                   fontWeight = FontWeight.Bold)
           )
           TextField(
               value = textFieldState,
               label = { Text("Enter your name") }, // Like Hint
               onValueChange = { textFieldState = it },
               singleLine = true,
               modifier = Modifier.fillMaxWidth()
           )
           Spacer(modifier = Modifier.height(10.dp))
           Button(modifier = Modifier.fillMaxWidth(),
               onClick = { onClick(textFieldState); textFieldState = "" } ) {
               Text(text = "Add")
           }
           Spacer(modifier = Modifier.height(16.dp))
           for (topic in subs)
               Text(topic)
       }
   }
}
