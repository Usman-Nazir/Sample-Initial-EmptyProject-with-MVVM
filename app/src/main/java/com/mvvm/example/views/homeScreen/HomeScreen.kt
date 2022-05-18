package com.mvvm.example.views.homeScreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.mvvm.example.R
import com.mvvm.example.common.AppController
import com.mvvm.example.databinding.ActivityHomeScreenBinding
import com.mvvm.example.views.dialogueFragments.LogoutDialogue
import com.mvvm.example.views.login.LoginScreen


class HomeScreen : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeScreenBinding
    var navController :NavController?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState)

        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHomeScreen.toolbar)

//        binding.appBarHomeScreen.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        navController= findNavController(R.id.nav_host_fragment_content_home_screen)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController!!, appBarConfiguration)
        navView.setupWithNavController(navController!!)


        binding.openHomeScreen.setOnClickListener { openHomeScreenLogic() }
        binding.logout.setOnClickListener { logoutLogic() }

    }

    private fun logoutLogic() {
        closeDrawer()
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        val prev = fm.findFragmentByTag("LogoutDialogue")
        if (prev != null) supportFragmentManager.beginTransaction().remove(prev).commit()
        val frag = LogoutDialogue.newInstance {
            AppController.getMyPrefManager()?.isLogin = false
            AppController.getMyPrefManager()?.modelLogin = null
            finishAffinity()
            startActivity(Intent(this,LoginScreen::class.java))
            finish()
        }
        frag.show(ft, "LogoutDialogue")
    }


    private fun openHomeScreenLogic() {
        navController?.navigate(R.id.nav_home)
        closeDrawer()
    }

    fun openDrawer(){
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }
    fun closeDrawer(){
        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    fun popUpStack(){
        navController?.popBackStack()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_screen, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home_screen)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}