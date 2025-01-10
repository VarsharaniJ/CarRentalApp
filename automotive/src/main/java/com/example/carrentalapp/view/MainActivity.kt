package com.example.carrentalapp.view

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import androidx.appcompat.app.AppCompatActivity
import com.example.carrentalapp.application.SpeedLimitManager
import com.example.carrentalapp.data.ApiService
import com.example.carrentalapp.data.FirebaseNotificationManager
import com.example.carrentalapp.data.NotificationRepositoryImpl
import com.example.carrentalapp.data.SpeedLimitRepositoryImpl
import com.example.carrentalapp.entities.CustomerType
import com.example.carrentalapp.entities.Renter
import com.example.carrentalapp.services.AAOSSpeedProvider
import com.example.carrentalapp.services.ServiceComponents
import com.example.carrentalapp.services.SpeedMonitoringService
import com.example.carrentalapp.usecase.GetSpeedLimitUseCase
import com.example.carrentalapp.usecase.NotifySpeedLimitExceededUseCase
import com.example.carrentalapp.usecase.SpeedChecker
import com.example.carrentalapp.viewmodel.SpeedMonitoringViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SpeedMonitoringViewModel
    private lateinit var speedMonitoringService: SpeedMonitoringService
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as SpeedMonitoringService
            speedMonitoringService = binder
            viewModel = SpeedMonitoringViewModel(ServiceComponents.speedLimitManager, speedMonitoringService )
            viewModel.startMonitoring("1", Renter(id = "1", name = "Varsha", "123466789",customerType = CustomerType.PREMIUM))

            viewModel.speed.observe(this@MainActivity) { speed ->
                println("Current speed: $speed km/h")
            }

            viewModel.notification.observe(this@MainActivity) { notification ->

            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = ApiService()
        val speedLimitRepository = SpeedLimitRepositoryImpl(apiService)
        val getSpeedLimitUseCase = GetSpeedLimitUseCase(speedLimitRepository)
        val notificationRepository =
            NotificationRepositoryImpl(ServiceComponents.firebaseNotificationManager)
        val notifySpeedLimitExceededUseCase =
            NotifySpeedLimitExceededUseCase(notificationRepository)
        val notificationHelper = NotificationHelper(this)

        ServiceComponents.speedLimitManager = SpeedLimitManager(
            getSpeedLimitUseCase,
            notifySpeedLimitExceededUseCase,
            notificationHelper
        )
        ServiceComponents.firebaseNotificationManager = FirebaseNotificationManager()

        // Start the service
        val intent = Intent(this, SpeedMonitoringService::class.java)
        bindService(intent, serviceConnection, BIND_AUTO_CREATE)

    }

    override fun onDestroy() {
        super.onDestroy()
        // Unbind the service
        unbindService(serviceConnection)
    }
}
