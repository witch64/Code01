package com.example.code00.Feedback

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FeedbackViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is feedback Fragment"
    }
    val text: LiveData<String> = _text
}