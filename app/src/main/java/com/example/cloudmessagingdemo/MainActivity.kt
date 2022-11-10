package com.example.cloudmessagingdemo

import android.media.session.MediaSession
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.cloudmessagingdemo.ui.layout.MainScreen
import com.example.cloudmessagingdemo.ui.theme.CloudMessagingDemoTheme
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<MainViewModel>()

        setContent {
            CloudMessagingDemoTheme {
                val fcmToken by viewModel.getFcmToken().observeAsState("")
                val subs by viewModel.getSubs().observeAsState(emptyList<String>())
                MainScreen(fcmToken,subs,{if (it.length>=3) viewModel.subscribeTopic(it)}) { }
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}






