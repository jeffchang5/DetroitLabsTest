package io.jeffchang.detroitlabschallenge

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.main_toolbar.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(main_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false);
//        supportFragmentManager.beginTransaction().replace()
    }

    override fun onResume() {
        super.onResume()
        setCurrentDate()
    }

    private fun setCurrentDate() {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("EEEE, MMMM dd", Locale.getDefault())
        main_toolbar_date_textview.text = sdf.format(calendar.time)
    }
}
