package com.example.lf_wannabe.loginmodule.login

import android.content.pm.PackageInstaller
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.lf_wannabe.loginmodule.R
import com.kakao.auth.ISessionCallback
import com.kakao.util.exception.KakaoException
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import kotlinx.android.synthetic.main.activity_login.*
import android.content.Intent
import android.util.Log
import com.kakao.auth.Session
import com.kakao.util.helper.log.Logger
import com.kakao.auth.network.response.AccessTokenInfoResponse
import com.kakao.network.ErrorResult
import com.kakao.auth.ApiResponseCallback
import com.kakao.auth.AuthService




/**
 * Created by lf_wannabe on 03/09/2017.
 */
class LoginActivity: AppCompatActivity() {
    private lateinit var callback: SessionCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 다른 클래스로 뽑아내는 방법이 없을까?
        naver.setOAuthLoginHandler(object: OAuthLoginHandler() {
            override fun run(success: Boolean) {
                var status = ""
                if(success){
                    with(OAuthLogin.getInstance()){
                        status += "status : ${getState(this@LoginActivity)}\n" +
                                "accessToken : ${getAccessToken(this@LoginActivity)}\n" +
                                "refreshToken : ${getRefreshToken(this@LoginActivity)}\n" +
                                "expiresAt : ${getExpiresAt(this@LoginActivity)}\n" +
                                "tokenType : ${getTokenType(this@LoginActivity)}"
                    }
                } else {
                    with(OAuthLogin.getInstance()){
                        status += "errorCode : ${getLastErrorCode(this@LoginActivity)}\n" +
                                "errorDesc : ${getLastErrorDesc(this@LoginActivity)}\n"
                    }
                }

                login_status.setText(status)
            }
        })

        callback = SessionCallback()
        Session.getCurrentSession().addCallback(callback)

    }

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(callback)
    }

    inner class SessionCallback : ISessionCallback {
        override fun onSessionOpened() {
            redirectSignupActivity("kakao")
            Toast.makeText(applicationContext, "세션 생성====", Toast.LENGTH_SHORT).show()
        }

        override fun onSessionOpenFailed(exception: KakaoException?) {
            Toast.makeText(applicationContext, "세션 실패====", Toast.LENGTH_SHORT).show()

            exception?.let { Log.d("MIM", exception.toString()) }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    fun redirectSignupActivity(loginFlag: String) {
        val intent = Intent(this, KakaoSignupActivity::class.java)
//        intent.putExtra("login", loginFlag)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
        finish()

    }

    fun onClickLogin(v: View){
        when (v.id) {
            R.id.facebook -> Toast.makeText(this, "아직 ㅠ_ㅠ ", Toast.LENGTH_SHORT).show()
        }

    }
}