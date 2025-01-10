package com.example.carrentalapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrentalapp.application.SpeedLimitManager
import com.example.carrentalapp.entities.CustomerType
import com.example.carrentalapp.entities.Renter
import com.example.carrentalapp.usecase.GetSpeedLimitUseCase
import com.example.carrentalapp.usecase.NotifySpeedLimitExceededUseCase
import com.example.carrentalapp.services.AAOSSpeedProvider
import com.example.carrentalapp.services.SpeedMonitoringService


class SpeedMonitoringViewModel(

    private val speedMonitoringService: SpeedMonitoringService

) : ViewModel() {

    private val _speed = MutableLiveData<Float>()
    val speed: LiveData<Float> get() = _speed

    private val _notification = MutableLiveData<String>()
    val notification: LiveData<String> get() = _notification

    init {
        // Observe speed updates from the service
        speedMonitoringService.speedLiveData.observeForever { vehicleSpeed ->
            _speed.postValue(vehicleSpeed)
            // Check and notify if speed exceeds limit
          //  speedLimitManager?.checkAndNotify("CAR123", Renter(id = "1", name = "John","", customerType = CustomerType.PREMIUM), vehicleSpeed.toInt())
        }
    }

    fun startMonitoring(carId: String, renter: Renter) {
        speedMonitoringService.startMonitoring(carId, renter)
    }

    override fun onCleared() {
        super.onCleared()
        speedMonitoringService.speedLiveData.removeObserver { }
    }
}
