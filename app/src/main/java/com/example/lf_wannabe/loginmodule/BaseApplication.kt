package com.example.lf_wannabe.loginmodule

import android.app.Application
import com.example.lf_wannabe.loginmodule.login.KakaoSDKAdapter
import com.kakao.auth.*
import com.nhn.android.naverlogin.OAuthLogin

/**
 * Created by lf_wannabe on 03/09/2017.
 */
class BaseApplication: Application() {
    private val OAUTH_CLIENT_ID = "iMUNO4r8OAvcjc0tL9Br"
    private val OAUTH_CLIENT_SECRET = "EHVRGC_hSb"
    private val OAUTH_CLIENT_NAME = "Minding"

    companion object {
        private lateinit var instance: BaseApplication
        var prefs: ApplicationPreferences? = null

        fun getApplicationContext(): BaseApplication {
//            if(instance != null)
            return instance

        }

    }

    override fun onCreate() {
        instance = this
        prefs = ApplicationPreferences(applicationContext)
        super.onCreate()

        initLogin()
    }

    fun initLogin() {
        //Naver
        OAuthLogin.getInstance().init(this, OAUTH_CLIENT_ID,
                OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME)

        //Kakao
        KakaoSDK.init(KakaoSDKAdapter())
    }
}