package com.ipotoday.ipotoday.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    val launchLiveData = MutableLiveData<Boolean>()

    private fun homeLaunch() {
        viewModelScope.launch {
            delay(SPLASH_TIME)
            launchLiveData.postValue(true)
        }
    }

    companion object {
        private const val SPLASH_TIME = 2000L
    }

    init {
        launchLiveData.postValue(false)
        homeLaunch()
    }
}