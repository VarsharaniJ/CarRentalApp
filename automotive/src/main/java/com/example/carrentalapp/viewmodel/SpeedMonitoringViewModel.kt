package com.example.carrentalapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrentalapp.services.SpeedMonitoringService

/**
 * ViewModel for managing vehicle speed monitoring and notifications.

 */
class SpeedMonitoringViewModel(
    private val speedMonitoringService: SpeedMonitoringService
) : ViewModel() {

    // LiveData to expose the current vehicle speed to the UI
    private val _speed = MutableLiveData<Float>()
    val speed: LiveData<Float> get() = _speed

    init {
        // Observe speed updates from the service
        speedMonitoringService.speedLiveData.observeForever { vehicleSpeed ->
            _speed.postValue(vehicleSpeed)
        }
    }

    override fun onCleared() {
        super.onCleared()
        // Removes observers from the `SpeedMonitoringService`
        speedMonitoringService.speedLiveData.removeObserver { }
    }
}
