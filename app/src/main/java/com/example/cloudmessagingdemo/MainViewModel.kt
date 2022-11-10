package com.example.cloudmessagingdemo

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    private val fcmToken = MutableLiveData<String>()
    fun getFcmToken() : LiveData<String> = fcmToken

    private val subs = mutableListOf<String>()
    private val subsLiveData = MutableLiveData<List<String>>()
    fun getSubs() : LiveData<List<String>> = subsLiveData.map { it.toMutableStateList() }

    /** ====================================== fetchFCMToken ==================================== */
    fun fetchFcmToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            fcmToken.value = task.result
            // Log fcm Token
            Log.d(TAG, "FCM Token: "+ task.result )
        })
    }

    /** =================================== subscribeTopic ====================================== */
    fun subscribeTopic(topic: String) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic)   // TOPIC = Breakfast
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    subs += topic
                    subsLiveData.postValue(subs)
                    Log.d(TAG, "Subscription successful: $topic")
                } else {
                    Log.d(TAG, "Subscription failed: $topic")
                }
            }
    }

    init {
        fetchFcmToken()
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}