package com.example.shoppinlist.viewmodel

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.AndroidViewModel
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModel(application: Application) :
    AndroidViewModel(application),
    CoroutineScope {

    val resources: Resources
        get() = getApplication<Application>().resources

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Unconfined

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
