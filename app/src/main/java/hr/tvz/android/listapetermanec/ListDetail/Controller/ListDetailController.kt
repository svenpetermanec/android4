package hr.tvz.android.listapetermanec.ListDetail.Controller

import android.content.Context
import androidx.room.Room
import hr.tvz.android.listapetermanec.ListDetail.View.IListDetailView
import hr.tvz.android.listapetermanec.Main.Helper.BazaPodataka
import hr.tvz.android.listapetermanec.ListDetail.Model.Exerc

class ListDetailController : IListDetailController {
    lateinit var listDetailView: IListDetailView
    constructor(listDetailView: IListDetailView) {
        this.listDetailView = listDetailView
    }

    override fun getExerc(id: String, context: Context): Exerc {
        val db = Room.databaseBuilder(context, BazaPodataka::class.java, "newTest").allowMainThreadQueries().build()
        return db.exercDao().getAll()[id.toInt()]
    }
}