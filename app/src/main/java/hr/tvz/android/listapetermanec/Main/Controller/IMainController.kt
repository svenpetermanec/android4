package hr.tvz.android.listapetermanec.Main.View

import android.content.Context
import hr.tvz.android.listapetermanec.ListDetail.Model.ExercDao

interface IMainController {
    fun getImages(exercDao: ExercDao)
    fun playMusic(context: Context)
}