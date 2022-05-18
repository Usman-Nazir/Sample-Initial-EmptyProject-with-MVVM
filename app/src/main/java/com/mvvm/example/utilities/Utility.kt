package com.mvvm.example.utilities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import com.mvvm.example.R
import com.kaopiz.kprogresshud.KProgressHUD
import java.io.File
import java.io.IOException
import java.math.BigInteger
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class Utility {
    companion object {
        var pg: KProgressHUD? = null

        @JvmStatic
        fun showProgressD(context: Context, msg: String = "Please wait") {
            hideProgressD(context)
            pg = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(msg ,context.resources.getColor(R.color.white))
                .setBackgroundColor(context.resources.getColor(R.color.redNewTheme))
                //.setDetailsLabel("Downloading data")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
            pg!!.show()
        }

        @JvmStatic
        fun hideProgressD(context: Context) {
            if (pg != null && pg?.isShowing ==true) pg?.dismiss()
        }

        @JvmStatic
        fun shareText(context: Context) {
            val shareBody = "Here is the share content body"
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Share")
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            context.startActivity(Intent.createChooser(sharingIntent, "Share our app to other users"))
        }

        @JvmStatic
        fun encryptThisString(input: String): String {
            try {
                // getInstance() method is called with algorithm SHA-512
                val md = MessageDigest.getInstance("SHA-512")

                // digest() method is called
                // to calculate message digest of the input string
                // returned as array of byte
                val messageDigest = md.digest(input.toByteArray())

                // Convert byte array into signum representation
                val no = BigInteger(1, messageDigest)

                // Convert message digest into hex value
                /*var hashtext = no.toString(16)

                Log.i("test" ,"hashtext.length ${hashtext.length}")
                // Add preceding 0s to make it 32 bit
                while (hashtext.length < 32) {
                    hashtext = "0$hashtext"
                }*/
                var hashtext = String.format("%0" + (messageDigest.size shl 1) + "X", no)

                // return the HashText
                return hashtext
            } catch (e: NoSuchAlgorithmException) {
                throw RuntimeException(e)
            }
            // For specifying wrong message digest algorithms
        }

//        @JvmStatic
//        fun showSnackBar(msg:String,parentLayout: View,context:Context){
//            //val parentLayout = findViewById<View>(android.R.id.content)
//            Snackbar.make(parentLayout, msg, Snackbar.LENGTH_LONG)
//                .setAction("CLOSE") { }
//                .setActionTextColor(context.resources.getColor(android.R.color.holo_red_light))
//                .show()
//        }

        @JvmStatic
        fun showSnackBarWithAction(msg:String,parentLayout: View,context:Context , action:String, callBack:()->Unit){
            //val parentLayout = findViewById<View>(android.R.id.content)
            Snackbar.make(parentLayout, msg, Snackbar.LENGTH_LONG)
                .setAction(action) {callBack.invoke() }
                .setActionTextColor(context.resources.getColor(android.R.color.holo_red_light))
                .show()
        }

        @JvmStatic
        fun isValidEmail(target: CharSequence): Boolean {
            return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }

        @JvmStatic
        fun hideKeyboard(activity: Activity) {
            val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            //Find the currently focused view, so we can grab the correct window token from it.
            var view = activity.getCurrentFocus()
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
        }

        @JvmStatic
        fun handleNetworkErrors(e: Exception, context_: Activity) {
            Log.i("test", " e $e")
            Handler(Looper.getMainLooper()).post {
                when (e) {
                    is UnknownHostException,is NoRouteToHostException -> context_.showSnackBar("Please check internet connections")
                    is SocketTimeoutException -> context_.showSnackBar("Connection time out, please try again")
                    else -> context_.showSnackBar("Oops other error we are checking")
                }
            }
        }

        @JvmStatic
        fun maxSizeImage(imagePath: String?): Boolean {
            var temp = false
            val file = File(imagePath)
            val length: Long = file.length()
            Log.i("test", "image size ${length}  file exists ${file.exists()}")
            if (length < (5 * 1048576)) // 5 mb
                temp = true
            return temp
        }

        @JvmStatic
        fun getImagePath(contentURI: Uri , context: Context , fileName:String): String? {
            var f: File? = null
            return try {
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val cursor = context.contentResolver?.query(contentURI, filePathColumn, null, null, null)
                cursor?.moveToFirst()
                val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
                val picturePath = columnIndex?.let { cursor.getString(it) }
                f = File(picturePath)
//                Log.i("test", "file path  ${f.exists()}  ${f.absolutePath}")

                var dir =File(context.applicationContext.cacheDir ,"temp")
                dir.mkdir()
                var file =File(dir ,fileName + "."+ f.absolutePath.split(".").lastOrNull() )
                if( !file.exists()) file.createNewFile()


                f?.copyTo(file ,true).absolutePath
//                file.absolutePath

            } catch (e: IOException) {
                Log.i("test", "eeeeeeeeeee  ${e}")
                ""
            }
        }

    }


}
