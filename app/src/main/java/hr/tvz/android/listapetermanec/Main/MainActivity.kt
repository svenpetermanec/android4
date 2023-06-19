package hr.tvz.android.listapetermanec.Main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.facebook.drawee.backends.pipeline.Fresco
import hr.tvz.android.listapetermanec.Main.Helper.BazaPodataka
import hr.tvz.android.listapetermanec.ListDetail.DetailFragment
import hr.tvz.android.listapetermanec.ListDetail.DetailsActivity
import hr.tvz.android.listapetermanec.Main.Model.Exercise
import hr.tvz.android.listapetermanec.ListDetail.ListFragment
import hr.tvz.android.listapetermanec.Main.Controller.MainController
import hr.tvz.android.listapetermanec.Main.View.IMainView
import hr.tvz.android.listapetermanec.R
import hr.tvz.android.listapetermanec.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), ListFragment.Callbacks, IMainView {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        // Init
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        Fresco.initialize(this)
        setContentView(binding.root)

        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val db = Room.databaseBuilder(applicationContext, BazaPodataka::class.java, "newTest")
            .allowMainThreadQueries().build()
        val exercDao = db.exercDao()


        // Fetch images
        val mainController = MainController(this)
        mainController.getImages(exercDao)


        // Background music
        mainController.playMusic(this)

        // Fragment
        supportFragmentManager.beginTransaction().add(R.id.item_list_container, ListFragment())
            .commit()
        if (findViewById<FrameLayout>(R.id.item_detail_container) != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.item_detail_container, DetailFragment()).commit()
        }
    }


    override fun onItemSelected(id: String?) {
        val arguments = Bundle()
        arguments.putString("id", id)
        val detailFragment = DetailFragment()
        detailFragment.arguments = arguments
        if (id != null) {
            if (findViewById<FrameLayout>(R.id.item_detail_container) != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.item_detail_container, detailFragment).commit()
            } else {
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra("exercise", Exercise(id.toInt() + 1))
                this.startActivity(intent)
            }
        }
    }
}