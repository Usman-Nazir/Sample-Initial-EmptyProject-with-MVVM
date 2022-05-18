package com.mvvm.example.views.splashScreen

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.mvvm.example.R
import com.mvvm.example.databinding.ActivitySplashScreenBinding
import com.mvvm.example.views.introScreen.IntroScreen

class SplashScreen : AppCompatActivity() {

    var binding: ActivitySplashScreenBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //  set status text dark
            //        window.statusBarColor = ContextCompat.getColor(this,R.color.colorPrimaryDark)// set status background white
        }

        supportActionBar?.hide()
        inits()
        handler = Handler(Looper.getMainLooper())
    }

    private fun inits() {
    }

    var runnable = Runnable {
        startActivity(Intent(this, IntroScreen::class.java))
        finish()
    }
    var handler: Handler? = null

    override fun onResume() {
        super.onResume()
        handler?.removeCallbacks(runnable)
        handler?.postDelayed(runnable, 3000)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}