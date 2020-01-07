package com.example.code00

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val toolbar = findViewById(R.id.toolbar) as Toolbar?
        //setSupportActionBar(toolbar)

        val navView = findViewById(R.id.nav_view) as NavigationView
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, 0,
           0)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)


        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                PlayFragment()).commit()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem) : Boolean{
        when (item.itemId) {
            R.id.nav_play -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                    PlayFragment()).commit()
            }
            R.id.nav_leaderboard -> {
                Toast.makeText(this,"LeaderBoard", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_feedback -> {
                Toast.makeText(this,"Feedback", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_profile -> {
                Toast.makeText(this,"Profile", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_aboutUs -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                    AboutUsFragment()).commit()
            }
            R.id.nav_recommended -> {
                Toast.makeText(this,"Recommended", Toast.LENGTH_SHORT).show()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }
}
