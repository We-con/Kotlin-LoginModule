package com.example.lf_wannabe.loginmodule.login

import android.content.Context
import com.example.lf_wannabe.loginmodule.BaseApplication
import com.kakao.auth.*

/**
 * Created by lf_wannabe on 03/09/2017.
 */
class KakaoSDKAdapter : KakaoAdapter() {
    override fun getSessionConfig(): ISessionConfig {
        return object : ISessionConfig {
            override fun isSaveFormData(): Boolean {
                return true
            }
            override fun getAuthTypes(): Array<AuthType> {
                return Array<AuthType>(1) { AuthType.KAKAO_LOGIN_ALL}
            }

            override fun isSecureMode(): Boolean {
                return false
            }
            override fun getApprovalType(): ApprovalType {
                return ApprovalType.INDIVIDUAL
            }
            override fun isUsingWebviewTimer(): Boolean {
                return false
            }
        }
    }

    override fun getApplicationConfig(): IApplicationConfig {
        return object : IApplicationConfig {
            override fun getApplicationContext(): Context {
                return BaseApplication.getApplicationContext().applicationContext
            }
        }
    }
}