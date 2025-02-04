# Car Rental App: which monitor speed and Notify user and Fleet company

This project is a simplified implementation of a system for a car rental company that monitors vehicle speeds and sends notifications if a renter exceeds a predefined speed limit. The solution follows clean architecture principles to separate concerns into domain, data, and presentation layers.

## Use Case

- **Objective:**  
  The fleet company sets a maximum permitted speed for each rental period. The maximum speed may differ for different customer types (e.g., PREMIUM or STANDARD). If a car’s speed exceeds this limit, the rental company is notified (via a notification channel) and the user receives a warning alert.

- **Notification Channels:**  
  The system initially uses Firebase for notifications, if firebase not available will send notification using AWS.

## How It Works

1. **Initialization:**
   - `CarRentalApp.kt` initializes the AAOSSpeedProvider with the application context.
  
2. **Data Flow:**
   - **SpeedMonitoringService**: Runs as a foreground service and listens for speed updates by AAOSSpeedProvider.
   - **SpeedMonitoringViewModel**: Exposes the live speed data from the service.
   - **SpeedLimitManager**: When a new speed is received, this component fetches the allowed speed limit (via the `GetSpeedLimitUseCase`) and, if the current speed exceeds the limit, sends a notification (via the `NotifySpeedLimitExceededUseCase`) and shows a warning to the user.
  
3. **Notification Channels:**
   - The `NotificationChannelFactory` decides at runtime whether to use Firebase or AWS to send notifications.
  
4. **Use Case Separation:**
   - The project uses separate use cases for retrieving speed limits and sending notifications, making the business logic easier to test and maintain.

## Assumptions

- Notification delivery is simulated with console output (`println`), and no real Firebase or AWS integration is performed.
- Dependency provided manually.
- ApiService class provide mock implementation, the speed limit is determined based on the customer's type (either PREMIUM or STANDARD).
