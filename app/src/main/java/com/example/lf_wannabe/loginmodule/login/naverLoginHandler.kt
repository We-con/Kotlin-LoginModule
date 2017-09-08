package com.example.lf_wannabe.loginmodule.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.lf_wannabe.loginmodule.MainActivity
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler

/**
 * Created by lf_wannabe on 04/09/2017.
 */
class naverLoginHandler(var ac: Activity) : OAuthLoginHandler() {
    override fun run(success: Boolean) {
        var status = ""
        if(success){
            with(OAuthLogin.getInstance()){
                status += "status : ${getState(ac.applicationContext)}\n" +
                        "accessToken : ${getAccessToken(ac.applicationContext)}\n" +
                        "refreshToken : ${getRefreshToken(ac.applicationContext)}\n" +
                        "expiresAt : ${getExpiresAt(ac.applicationContext)}\n" +
                        "tokenType : ${getTokenType(ac.applicationContext)}"

                redirectMainActivity(status)
            }
        } else {
            with(OAuthLogin.getInstance()){
                status += "errorCode : ${getLastErrorCode(ac.applicationContext)}\n" +
                        "errorDesc : ${getLastErrorDesc(ac.applicationContext)}\n"

                redirectLoginActivity()
            }
        }

    }

    private fun redirectMainActivity(reponse: String) {
        var intent = Intent(ac, MainActivity::class.java)
        intent.putExtra("response", reponse)
        ac.startActivity(intent)
        ac.finish()
    }

    protected fun redirectLoginActivity() {
        val intent = Intent(ac, LoginActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        ac.startActivity(intent)
        ac.finish()
    }
}