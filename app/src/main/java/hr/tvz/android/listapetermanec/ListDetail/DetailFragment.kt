package hr.tvz.android.listapetermanec.ListDetail

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.facebook.drawee.view.SimpleDraweeView
import hr.tvz.android.listapetermanec.Main.Helper.BazaPodataka
import hr.tvz.android.listapetermanec.ListDetail.Model.Exerc
import hr.tvz.android.listapetermanec.R

class DetailFragment : Fragment() {
    private var exerc: Exerc = Exerc("99", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(requireActivity().applicationContext, BazaPodataka::class.java, "newTest").allowMainThreadQueries().build()
        val exercDao = db.exercDao()

        if(arguments?.containsKey("id") == true) {
            val id = requireArguments().getString("id")?.toInt()
            exerc = exercDao.getAll()[id!!]
        }
    }

    @SuppressLint("MissingInflatedId", "DiscouragedApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TESTHERE", exerc.id)
        val root =inflater.inflate(R.layout.item_detail_fragment, container, false)
        root.findViewById<TextView>(R.id.item_detail).text = exerc.name
        val uri: Uri = Uri.parse(exerc.description)
        root.findViewById<SimpleDraweeView>(R.id.imageView).setImageURI(uri)
        return root
    }
}