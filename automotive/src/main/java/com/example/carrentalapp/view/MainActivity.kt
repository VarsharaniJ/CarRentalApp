package com.example.carrentalapp.view

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.example.carrentalapp.application.SpeedLimitManager
import com.example.carrentalapp.data.ApiService
import com.example.carrentalapp.data.FirebaseNotificationManager
import com.example.carrentalapp.data.NotificationRepositoryImpl
import com.example.carrentalapp.data.SpeedLimitRepositoryImpl
import com.example.carrentalapp.entities.CustomerType
import com.example.carrentalapp.entities.Renter
import com.example.carrentalapp.services.ServiceComponents
import com.example.carrentalapp.services.SpeedMonitoringService
import com.example.carrentalapp.usecase.GetSpeedLimitUseCase
import com.example.carrentalapp.usecase.NotifySpeedLimitExceededUseCase
import com.example.carrentalapp.viewmodel.SpeedMonitoringViewModel

/**
 * The main activity of the car rental app that initializes services, manages the ViewModel,
 * and monitors vehicle speed.
 */
class MainActivity : AppCompatActivity() {

    // ViewModel for speed monitoring
    private lateinit var viewModel: SpeedMonitoringViewModel
    // Instance of the speed monitoring service
    private lateinit var speedMonitoringService: SpeedMonitoringService
    // Service connection to bind and communicate with the speed monitoring service
    private val serviceConnection = object : ServiceConnection {

        // Called when the service is connected. Initializes the ViewModel and starts speed monitoring.
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as SpeedMonitoringService.LocalBinder
            speedMonitoringService = binder.getService()
            viewModel = SpeedMonitoringViewModel(speedMonitoringService )
            viewModel.startMonitoring("1", Renter(id = "1", name = "Varsha", "123466789",customerType = CustomerType.PREMIUM))

            // Observe speed changes
            viewModel.speed.observe(this@MainActivity) { speed ->
                println("Current speed: $speed km/h")
            }

            // Observe notifications for speed limit exceeded
            viewModel.notification.observe(this@MainActivity) { notification ->

            }
        }
        //Called when the service is disconnected. Cleans up references if necessary.
        override fun onServiceDisconnected(name: ComponentName?) {
            // Handle service disconnection logic
        }
    }

    /**
     * Called when the activity is created. Initializes dependencies, services, and bindings.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize dependencies for the speed limit manager
        val apiService = ApiService()
        val speedLimitRepository = SpeedLimitRepositoryImpl(apiService)
        val getSpeedLimitUseCase = GetSpeedLimitUseCase(speedLimitRepository)
        val notificationRepository =
            NotificationRepositoryImpl(ServiceComponents.firebaseNotificationManager)
        val notifySpeedLimitExceededUseCase =
            NotifySpeedLimitExceededUseCase(notificationRepository)
        val notificationHelper = NotificationHelper(this)

        // Set up the global service components
        ServiceComponents.speedLimitManager = SpeedLimitManager(
            getSpeedLimitUseCase,
            notifySpeedLimitExceededUseCase,
            notificationHelper
        )
        ServiceComponents.firebaseNotificationManager = FirebaseNotificationManager()

        // Start and bind the speed monitoring service
        val intent = Intent(this, SpeedMonitoringService::class.java)
        bindService(intent, serviceConnection, BIND_AUTO_CREATE)

    }

    override fun onDestroy() {
        super.onDestroy()
        // Unbind the service perform cleanup
        unbindService(serviceConnection)
    }
}
