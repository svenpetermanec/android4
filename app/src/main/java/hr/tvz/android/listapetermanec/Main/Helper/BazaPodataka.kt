package hr.tvz.android.listapetermanec.Main.Helper

import androidx.room.Database
import androidx.room.RoomDatabase
import hr.tvz.android.listapetermanec.ListDetail.Model.Exerc
import hr.tvz.android.listapetermanec.ListDetail.Model.ExercDao

@Database(entities = [Exerc::class], version = 1)
abstract class BazaPodataka : RoomDatabase() {
    abstract fun exercDao(): ExercDao
}