package com.example.carrentalapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrentalapp.entities.Renter
import com.example.carrentalapp.services.SpeedMonitoringService

/**
 * ViewModel for managing vehicle speed monitoring and notifications.
 * @param speedMonitoringService The service responsible for providing speed updates.
 */
class SpeedMonitoringViewModel(

    private val speedMonitoringService: SpeedMonitoringService

) : ViewModel() {

    // LiveData to expose the current vehicle speed to the UI
    private val _speed = MutableLiveData<Float>()
    val speed: LiveData<Float> get() = _speed

    // LiveData to expose notifications speed limit exceeded alerts to the UI
    private val _notification = MutableLiveData<String>()
    val notification: LiveData<String> get() = _notification

    init {
        // Observe speed updates from the service
        speedMonitoringService.speedLiveData.observeForever { vehicleSpeed ->
            _speed.postValue(vehicleSpeed)
            // Check and notify if speed exceeds limit
        }
    }

    /**
     * Starts monitoring the speed of a specific vehicle for a given renter.
     *
     * @param carId The ID of the car being monitored.
     * @param renter The renter information associated with the car.
     */
    fun startMonitoring(carId: String, renter: Renter) {
        speedMonitoringService.startMonitoring(carId, renter)
    }


    override fun onCleared() {
        super.onCleared()
        // Removes observers from the `SpeedMonitoringService`
        speedMonitoringService.speedLiveData.removeObserver { }
    }
}
