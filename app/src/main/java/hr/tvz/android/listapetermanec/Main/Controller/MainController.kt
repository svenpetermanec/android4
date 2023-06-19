package hr.tvz.android.listapetermanec.Main.Controller

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import hr.tvz.android.listapetermanec.Main.Helper.API
import hr.tvz.android.listapetermanec.ListDetail.Model.Exerc
import hr.tvz.android.listapetermanec.ListDetail.Model.ExercDao
import hr.tvz.android.listapetermanec.Main.View.IMainController
import hr.tvz.android.listapetermanec.Main.View.IMainView
import hr.tvz.android.listapetermanec.R
import hr.tvz.android.listapetermanec.Main.Helper.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainController : IMainController {
    lateinit var mainView: IMainView
    constructor(mainView: IMainView) {
        this.mainView = mainView
    }

    override fun getImages(exercDao: ExercDao) {
        if (exercDao.getAll().isEmpty()) {
            val api = RetrofitHelper.getInstance().create(API::class.java)
            GlobalScope.launch {
                for(i in 1..3) {
                    var result = api.getImage()
                    var body = result.body()!!
                    exercDao.insertOne(Exerc(body.id, body.alt_description, body.urls.regular))
                }
            }
        }
    }

    override fun playMusic(context: Context) {
        var mediaPlayer = MediaPlayer.create(context, R.raw.background)
        mediaPlayer.isLooping = true
        mediaPlayer.setVolume(100F, 100F)
        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder()
            .setFlags(AudioAttributes.FLAG_AUDIBILITY_ENFORCED)
            .setLegacyStreamType(AudioManager.STREAM_MUSIC)
            .setUsage(AudioAttributes.USAGE_ALARM)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build())
        mediaPlayer.start()
    }
}