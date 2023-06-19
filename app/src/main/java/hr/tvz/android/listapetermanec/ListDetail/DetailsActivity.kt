package hr.tvz.android.listapetermanec.ListDetail

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.facebook.drawee.view.SimpleDraweeView
import hr.tvz.android.listapetermanec.ListDetail.Controller.ListDetailController
import hr.tvz.android.listapetermanec.ListDetail.View.IListDetailView
import hr.tvz.android.listapetermanec.Main.Helper.BazaPodataka
import hr.tvz.android.listapetermanec.Main.Model.Exercise
import hr.tvz.android.listapetermanec.R
import hr.tvz.android.listapetermanec.databinding.DetailsBinding

@Suppress("DEPRECATION")
class DetailsActivity : AppCompatActivity(), IListDetailView {
    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val exercise: Exercise = intent.getParcelableExtra("exercise")!!

        val listDetailController = ListDetailController(this)
        val exerc = listDetailController.getExerc((exercise.id - 1).toString(), applicationContext)

        val uri: Uri = Uri.parse(exerc.description)
        findViewById<SimpleDraweeView>(R.id.image).setImageURI(uri)
        binding.title.text =exerc.name
    }
}