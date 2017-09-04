package com.example.lf_wannabe.loginmodule.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.lf_wannabe.loginmodule.MainActivity
import com.kakao.auth.ErrorCode
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeResponseCallback
import com.kakao.usermgmt.response.model.UserProfile

/**
 * Created by lf_wannabe on 04/09/2017.
 */
class KakaoSignupActivity: AppCompatActivity(){
    /**
     * Main으로 넘길지 가입 페이지를 그릴지 판단하기 위해 me를 호출한다.
     * @param savedInstanceState 기존 session 정보가 저장된 객체
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestMe()
    }

    /**
     * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
     */
    protected fun requestMe() { //유저의 정보를 받아오는 함수
        UserManagement.requestMe(object: MeResponseCallback() {
            override fun onFailure(errorResult: ErrorResult?) {
                super.onFailure(errorResult)

                var message = "failed to get user info. msg=" + errorResult
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

                var result: ErrorCode = ErrorCode.valueOf(errorResult?.errorCode)
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    finish()
                } else {
                    redirectLoginActivity()
                }
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                redirectLoginActivity()
            }

            override fun onNotSignedUp() {
                // 카카오톡 회원이 아닐 시 showSignup(); 호출해야함
            }

            override fun onSuccess(result: UserProfile?) {
                Toast.makeText(applicationContext, "KAKAO : " + result, Toast.LENGTH_SHORT).show()
                redirectMainActivity("KAKAO : " + result) // 로그인 성공시 MainActivity로
            }
        })
    }

    private fun redirectMainActivity(reponse: String) {
        var intent = Intent(this, MainActivity::class.java)
        intent.putExtra("response", reponse)
        startActivity(intent)
        finish()
    }

    protected fun redirectLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
        finish()
    }

}