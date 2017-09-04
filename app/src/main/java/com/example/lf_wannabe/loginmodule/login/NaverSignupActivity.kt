package com.example.lf_wannabe.loginmodule.login

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler

/**
 * Created by lf_wannabe on 04/09/2017.
 */
class NaverSignupActivity: AppCompatActivity() {
    companion object {
//        var naverLoginHandler: OAuthLoginHandler = object: OAuthLoginHandler() {
//            override fun run(success: Boolean) {
//                var status = ""
//                if(success){
//                    with(OAuthLogin.getInstance()){
//                        status += "status : " + getState(this).toString() + "\n" +
//                                "accessToken : " + getAccessToken(this@Companion) + "\n" +
//                                "refreshToken : " + getRefreshToken(this@Companion) + "\n" +
//                                "expiresAt : " + getExpiresAt(this@Companion) + "\n" +
//                                "tokenType : " + getTokenType(this@Companion)
//                    }
//                } else {
//                    with(OAuthLogin.getInstance()){
//                        status += "errorCode : " + getLastErrorCode(this@Companion).toString() + "\n" +
//                                "errorDesc : " + getLastErrorDesc(this@LoginActivity) + "\n"
//                    }
//                }
//            }
//        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}