package com.example.dmap.Login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.dmap.Map.MainActivity
import com.example.dmap.R
import com.example.dmap.Signup.SignUpActivity
import com.example.dmap.User.User
import com.example.dmap.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.rx
import com.kakao.sdk.common.util.Utility
import io.reactivex.android.schedulers.AndroidSchedulers
import java.lang.Exception

class LoginActivity : AppCompatActivity() , View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel : LoginViewModel by lazy {
        LoginViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        observe()

        binding.loginKakao.setOnClickListener(this)

        loginViewModel.kakaoId.observe(this , Observer {

            try {
                User.userId = it
                loginViewModel.userLogin(it)
            }catch (e : Exception){

            }
        })
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()

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
                loginViewModel.getKakaoId(token = token.accessToken)
            } ,{
                error -> Log.e("LoginActivity" , error.toString())
            })

    }

    fun observe(){
        loginViewModel.userLogin.observe(this, Observer {
            if(it){
                //login 완료
                startActivity(Intent(this , MainActivity::class.java))
            }else{
                //회원 가입 하면으로 이동
                startActivity(Intent(this , SignUpActivity::class.java))
            }
        })
    }

}