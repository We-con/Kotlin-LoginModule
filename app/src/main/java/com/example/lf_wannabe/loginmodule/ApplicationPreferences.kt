package com.example.lf_wannabe.loginmodule

import android.content.Context
import android.content.SharedPreferences
import com.example.lf_wannabe.loginmodule.login.model.UserInfo

/**
 * Created by lf_wannabe on 08/09/2017.
 */
class ApplicationPreferences(context: Context){
    private val PREFS_FILENAME = "com.example.lf_wannabe.loginmodule"
    private val KEY_LOGIN_USER_NAME = "user_name"
    private val KEY_LOGIN_USER_THUMNAIL = "user_thumnail"

    private val KEY_LOGIN_TYPE = "sns_type"
    private val KEY_LOGIN_TOKEN = "user_token"
    private val KEY_LOGIN_TOKEN_TYPE = "user_token_type"


    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var userName: String
        get() = prefs.getString(KEY_LOGIN_USER_NAME, "")
        set(value) = prefs.edit().putString(KEY_LOGIN_USER_NAME, value).apply()

    var userThumnail: String
        get() = prefs.getString(KEY_LOGIN_USER_THUMNAIL, "")
        set(value) = prefs.edit().putString(KEY_LOGIN_USER_THUMNAIL, value).apply()

    var snsType: String
        get() = prefs.getString(KEY_LOGIN_TYPE, "")
        set(value) = prefs.edit().putString(KEY_LOGIN_TYPE, value).apply()

    var userInfo: UserInfo? = null
        get() = if(!userName.equals("")) UserInfo(userName, userThumnail) else null

    var userToken: String
        get() = prefs.getString(KEY_LOGIN_TOKEN, "")
        set(value) = prefs.edit().putString(KEY_LOGIN_TOKEN, value).apply()

    var userTokenType: String
        get() = prefs.getString(KEY_LOGIN_TOKEN_TYPE, "")
        set(value) = prefs.edit().putString(KEY_LOGIN_TOKEN_TYPE, value).apply()


    fun logout(){
        prefs.edit().clear().apply()
        userInfo = null
    }

}