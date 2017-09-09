package com.example.lf_wannabe.loginmodule.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.example.lf_wannabe.loginmodule.BaseApplication
import com.example.lf_wannabe.loginmodule.MainActivity
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler

/**
 * Created by lf_wannabe on 04/09/2017.
 */
class naverLoginHandler(var ac: Activity) : OAuthLoginHandler() {
    val mOAuthLoginInstance = OAuthLogin.getInstance()
    var user_profile = ""

    override fun run(success: Boolean) {
        if(success){
            with(mOAuthLoginInstance){
                with(BaseApplication.prefs!!){
                    snsType = "NAVER"
                    userToken = getAccessToken(ac.applicationContext)
                    userTokenType = getTokenType(ac.applicationContext)
                    //xml 파싱전 잠시
                    userName = getAccessToken(ac.applicationContext)
                }
                requestAPITask().execute()
                redirectMainActivity()
            }

        } else {
            with(mOAuthLoginInstance){
                Log.d("MIM_LOGIN", "errorCode : ${getLastErrorCode(ac.applicationContext)}\n" +
                        "errorDesc : ${getLastErrorDesc(ac.applicationContext)}\n")

                redirectLoginActivity()
            }
        }
    }

    inner class requestAPITask : AsyncTask<Void, String, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: Void?): String {
            val url = "https://apis.naver.com/nidlogin/nid/getUserProfile.xml"
            val at = mOAuthLoginInstance.getAccessToken(ac.applicationContext)
            user_profile = mOAuthLoginInstance.requestApi(ac.applicationContext, at, url)

            return user_profile
        }

        override fun onPostExecute(result: String?) {
            Toast.makeText(ac.applicationContext, result, Toast.LENGTH_SHORT).show()
        }
    }

    private fun redirectMainActivity() {
        var intent = Intent(ac, MainActivity::class.java)
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