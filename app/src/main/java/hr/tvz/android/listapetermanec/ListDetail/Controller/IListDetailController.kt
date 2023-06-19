package hr.tvz.android.listapetermanec.ListDetail.Controller

import android.content.Context
import hr.tvz.android.listapetermanec.ListDetail.Model.Exerc

interface IListDetailController {
    fun getExerc(id: String, context: Context): Exerc
}