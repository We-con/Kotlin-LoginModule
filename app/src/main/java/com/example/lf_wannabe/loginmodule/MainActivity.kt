package com.example.lf_wannabe.loginmodule

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.lf_wannabe.loginmodule.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        login_btn.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(p0: View?) {
//                intent = Intent(applicationContext, LoginActivity::class.java)
//                startActivity(intent)
//            }
//        })
        login_btn.setOnClickListener {
            intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }


        if(intent.extras != null){
            user_status.setText(intent.extras.getString("response"))
        }
    }
}
