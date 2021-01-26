package com.example.dmap

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.dmap.Signup.SignUpActivity
import com.example.dmap.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.rx
import com.kakao.sdk.common.util.Utility
import io.reactivex.android.schedulers.AndroidSchedulers

class LoginActivity : AppCompatActivity() , View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.loginKakao.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.loginKakao -> { kakaoLogin()}
        }
    }

    fun kakaoLogin(){

        val disposable =  LoginClient.rx.loginWithKakaoTalk(this)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ token ->
                // 토큰 받고 확인 후 회원가입으로 보냄
                startActivity(Intent(this , SignUpActivity::class.java))

                Log.i("LoginActivity", "Login 성공 ${token.accessToken}" )
            } ,{
                error -> Log.e("LoginActivity" , error.toString())
            })

    }
}