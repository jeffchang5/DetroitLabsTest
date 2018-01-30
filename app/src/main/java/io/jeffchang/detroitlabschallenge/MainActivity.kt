package io.jeffchang.detroitlabschallenge

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.jeffchang.detroitlabschallenge.ui.now.NowFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.activity_main_container,
                NowFragment.newInstance()).commit()
    }
}
