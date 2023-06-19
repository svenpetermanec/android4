package hr.tvz.android.listapetermanec.Widget

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.RemoteViews
import androidx.room.Room
import hr.tvz.android.listapetermanec.Main.Helper.BazaPodataka
import hr.tvz.android.listapetermanec.Main.MainActivity
import hr.tvz.android.listapetermanec.R
import java.net.URL

class ExampleAppWidgetProvider : AppWidgetProvider() {

    @SuppressLint("RemoteViewLayout")
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {

        appWidgetIds.forEach { appWidgetId ->
            val pendingIntent: PendingIntent = PendingIntent.getActivity(
                context,
                0,
                Intent(context, MainActivity::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val db = Room.databaseBuilder(context, BazaPodataka::class.java, "newTest")
                .allowMainThreadQueries().build()

            var bmp: Bitmap
            var exer = db.exercDao().getAll().last()

            val views: RemoteViews = RemoteViews(
                context.packageName,
                R.layout.appwidget_layout
            ).apply {
                bmp = BitmapFactory.decodeStream(URL(exer.description).openStream())
                setImageViewBitmap(R.id.imageView2, bmp)
                setTextViewText(R.id.textView2, exer.name)
            }

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
