package soup.gdg.navigation.sample

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

object NotificationChannels {

    private const val DEFAULT = "DEFAULT"

    private val Context.notificationManager: NotificationManager?
        get() = getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager

    internal fun initialize(application: Application) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val defaultChannel = NotificationChannel(
                DEFAULT, "Default", NotificationManager.IMPORTANCE_HIGH
            )
            application.notificationManager?.createNotificationChannels(listOf(defaultChannel))
        }
    }

    fun notify(context: Context, intercept: NotificationCompat.Builder.() -> NotificationCompat.Builder) {
        context.notificationManager?.notify(
            1,
            NotificationCompat
                .Builder(context, DEFAULT)
                .intercept()
                .build()
        )
    }
}