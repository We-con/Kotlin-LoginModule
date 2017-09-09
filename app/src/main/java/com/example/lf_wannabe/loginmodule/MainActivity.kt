package com.example.lf_wannabe.loginmodule

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.lf_wannabe.loginmodule.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(applicationContext, "userInfo : ${BaseApplication.prefs!!.userInfo}",
                Toast.LENGTH_SHORT).show()

        with(BaseApplication.prefs!!){
            userInfo?. let {
                sns_type.setText(snsType)
                it.name?. let {
                    user_status.setText(it)
                    login_btn.setText("log out")
                } ?: user_status.setText("unlogin")


                it.thumnail?. let {
                    Glide.with(applicationContext)
                            .load(BaseApplication.prefs!!.userThumnail)
                            .into(profile_thumnail)
                }
            }
        }
        BaseApplication.prefs!!.userInfo?. let {
            user ->
                user.name?. let {
                    user_status.setText(it)
                    login_btn.setText("log out")
                } ?: user_status.setText("unlogin")


                user.thumnail?. let {
                    Glide.with(applicationContext)
                            .load(BaseApplication.prefs!!.userThumnail)
                            .into(profile_thumnail)
                }
        }

        login_btn.setOnClickListener {
            BaseApplication.prefs!!.userInfo?. let {
                redirectLogoutActivity()
            } ?: redirectLoginAcitivity()
        }

        if(intent.extras != null){
            user_status.setText(intent.extras.getString("response"))
        }
    }

    private fun redirectLoginAcitivity(){
        startActivity(Intent(applicationContext, LoginActivity::class.java))
        finish()
    }

    private fun redirectLogoutActivity(){
        BaseApplication.prefs!!.logout()
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }
}
