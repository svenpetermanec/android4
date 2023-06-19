package hr.tvz.android.listapetermanec.ListDetail.Model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import hr.tvz.android.listapetermanec.ListDetail.Model.Exerc

@Dao
interface ExercDao {
    @Query("SELECT * FROM Exerc ORDER BY id ASC")
    fun getAll(): MutableList<Exerc>

    @Insert
    fun insertOne(exerc: Exerc)
}