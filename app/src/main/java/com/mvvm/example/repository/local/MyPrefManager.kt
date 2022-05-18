package com.mvvm.example.repository.local

import android.content.Context
import android.content.SharedPreferences
import com.mvvm.example.models.userLogin.ModelLogin
import hu.autsoft.krate.Krate
import hu.autsoft.krate.booleanPref
import hu.autsoft.krate.gson.gsonPref

class MyPrefManager(context: Context) : Krate {
    override val sharedPreferences: SharedPreferences = context.applicationContext.getSharedPreferences("PrefCustom", Context.MODE_PRIVATE)
    var isIntroShown by booleanPref("isIntroShown", false)
    var isRemember by booleanPref("isRemember", false)
    var isLogin by booleanPref("isLogin", false)
    var dontShowIntro by booleanPref("dontShowIntro", false)
    var modelLogin: ModelLogin? by gsonPref("modelLogin")


}