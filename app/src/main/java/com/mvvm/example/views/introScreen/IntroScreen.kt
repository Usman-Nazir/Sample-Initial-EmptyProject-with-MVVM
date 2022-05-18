package com.mvvm.example.views.introScreen

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.mvvm.example.R
import com.mvvm.example.common.AppController
import com.mvvm.example.databinding.ActivityIntroScreenBinding
import com.mvvm.example.views.adapters.IntroAdapter
import com.mvvm.example.views.adapters.IntroScreensModel
import com.mvvm.example.views.login.LoginScreen


class IntroScreen : AppCompatActivity() {
    var binding: ActivityIntroScreenBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState)
        binding = ActivityIntroScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //  set status text dark
        }
        supportActionBar?.hide()
        inits()

    }

    private fun inits() {

        binding?.backButton?.visibility = View.GONE
        binding?.skipIntro?.visibility = View.VISIBLE

        //initial setting
        binding?.dontShowAgain?.visibility = View.GONE
        binding?.backButton?.visibility = View.GONE
        binding?.skipIntro?.visibility = View.VISIBLE
//        binding?.bottomLayout?.orientation = LinearLayout.HORIZONTAL
//        binding?.nextButton?.text = "Next"



        val list: MutableList<IntroScreensModel> = arrayListOf()
//        list.add(IntroScreensModel(R.drawable.intro_0, "", "", 0))
        list.add(IntroScreensModel(R.drawable.log_out, "Title", "Lorem Ipsum.", 1))
        list.add(IntroScreensModel(R.drawable.log_out, "Title", "Lorem Ipsum.", 2))
        list.add(IntroScreensModel(R.drawable.log_out, "Title", "Lorem Ipsum", 3))

        val introAdapter = IntroAdapter(this, list)
        binding?.introPager?.adapter = introAdapter
        binding?.introPager?.let {
            binding?.dotsIndicator?.setViewPager2(it)
        }

        binding?.dontShowAgain?.setOnCheckedChangeListener { compoundButton, b ->
            AppController.prefManager?.dontShowIntro = b
        }

        binding?.introPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
//                    0 -> {
//                        binding?.dontShowAgain?.visibility = View.VISIBLE
//                        binding?.backButton?.visibility = View.GONE
//                        binding?.skipIntro?.visibility = View.VISIBLE
//                        binding?.bottomLayout?.orientation = LinearLayout.VERTICAL
//                        binding?.nextButton?.text = "Next"
//                    }
                    0 -> {
                        binding?.dontShowAgain?.visibility = View.GONE
                        binding?.backButton?.visibility = View.GONE
                        binding?.skipIntro?.visibility = View.VISIBLE
//                        binding?.bottomLayout?.orientation = LinearLayout.HORIZONTAL
//                        binding?.nextButton?.text = "Next"
                    }
                    1 -> {
                        binding?.dontShowAgain?.visibility = View.GONE
                        binding?.backButton?.visibility = View.VISIBLE
                        binding?.skipIntro?.visibility = View.VISIBLE
//                        binding?.bottomLayout?.orientation = LinearLayout.HORIZONTAL
//                        binding?.nextButton?.text = "Next"
                    }
                    2 -> {
                        binding?.dontShowAgain?.visibility = View.GONE
                        binding?.backButton?.visibility = View.VISIBLE
                        binding?.skipIntro?.visibility = View.VISIBLE
//                        binding?.bottomLayout?.orientation = LinearLayout.VERTICAL
//                        binding?.nextButton?.text = "Let's get started"
                    }
                }
            }
        })
        binding?.skipIntro?.setOnClickListener { skipIntroLogic() }
        binding?.backButton?.setOnClickListener { backButtonLogic() }
        binding?.introPager?.isUserInputEnabled = false
        binding?.nextButton?.setOnClickListener { nextButtonLogic() }
        binding?.getStarted?.setOnClickListener { getStartedLogic() }

//        bottomLayout


    }

    private fun getStartedLogic() {
        skipIntroLogic()
    }

    private fun nextButtonLogic() {

        binding?.introPager?.let {
            if(it.currentItem ==2){
                skipIntroLogic()
            }else{
                binding?.introPager?.setCurrentItem(++it.currentItem, true)
            }

        }
    }

    private fun backButtonLogic() {
        binding?.introPager?.let {
            binding?.introPager?.setCurrentItem(--it.currentItem, true)
        }

    }

    private fun skipIntroLogic() {
        startActivity(Intent(this, LoginScreen::class.java))
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}