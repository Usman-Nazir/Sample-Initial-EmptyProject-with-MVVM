package com.mvvm.example.views.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mvvm.example.common.AppController
import com.mvvm.example.databinding.ActivityLoginScreenBinding
import com.mvvm.example.models.generalError.ModelError
import com.mvvm.example.models.userLogin.ModelLogin
import com.mvvm.example.utilities.Utility
import com.mvvm.example.utilities.showSnackBar
import com.mvvm.example.views.homeScreen.HomeScreen


fun CharSequence?.isValidEmail() =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

class LoginScreen : AppCompatActivity() {

    var viewModel: LoginViewModel? = null
    var binding: ActivityLoginScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //  set status text dark
        }
        supportActionBar?.hide()
        inits()
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


    }

    private fun inits() {
        binding?.backButton?.setOnClickListener { super.onBackPressed() }
        binding?.loginButton?.setOnClickListener { loginButtonLogic() }

    }



    private fun loginButtonLogic() {
        binding?.passwordText?.error = null
        binding?.emailText?.error = null

        when {
            binding?.emailEdit?.text?.toString()?.isEmpty() == true -> binding?.emailText?.error = "Email Required"
            binding?.passwordEdit?.text?.toString()?.isEmpty() == true -> binding?.passwordText?.error = "Password Required"
            else -> {
                Utility.showProgressD(this,"Checking Credentials")
                viewModel
                    ?.loginWithCredentials(binding?.emailEdit?.text?.toString() ?: "", binding?.passwordEdit?.text?.toString() ?: "") { response ->
                        when (response) {
                            is ModelLogin -> {

                                AppController.getMyPrefManager()?.isLogin = true
                                AppController.getMyPrefManager()?.modelLogin = response
                                startActivity(Intent(this@LoginScreen, HomeScreen::class.java))
                                finish()
                            }
                            is ModelError -> {
                                showSnackBar(if (response.errors?.isNotEmpty() == true) (response.errors?.joinToString { it + "\n" } ?: "") else (response.message ?: ""))
                                startActivity(Intent(this@LoginScreen, HomeScreen::class.java))
                                finish()
                            }
                            is Exception -> {
                                Utility.handleNetworkErrors(response, this@LoginScreen)
                            }
                        }
                        Utility.hideProgressD(this)
                    }


//                loginProcessCall(true)
//                binding?.emailPhone?.error = "Email / Phone Required"
//                startActivity(Intent(this, ConfirmPasscodeScreen::class.java))
            }
        }
//        startActivity(Intent(this,))
    }

    private fun loginProcessCall(isEmail: Boolean) {


        startActivity(Intent(this, HomeScreen::class.java))
//        AppController.getRepository()?.localRepository?.prefManager?.isRemember = binding?.rememberMe?.isChecked ?: false
//        val intent = Intent(this, ConfirmPasscodeScreen::class.java)
//        intent.putExtra(ConfirmPasscodeScreen.emailTextArg, binding?.emailPhone?.text?.toString()?.trim())
//        intent.putExtra(ConfirmPasscodeScreen.isEmailArg, isEmail)
//        startActivity(intent)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}