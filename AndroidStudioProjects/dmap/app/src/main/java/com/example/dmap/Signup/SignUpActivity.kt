package com.example.dmap.Signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.dmap.Map.MainActivity
import com.example.dmap.R
import com.example.dmap.Signup.network.SignupRequest
import com.example.dmap.User.User
import com.example.dmap.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() , View.OnClickListener {

    lateinit var binding : ActivitySignUpBinding
    private var genderValue = -1;
    private var nickNameCheck = false
    private var genderButtons = mutableListOf(R.id.genderMaleBtn , R.id.genderFemaleBtn)

    lateinit var kakaoId : String

    val signupViewModel : SignUpViewModel by lazy {
        SignUpViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.idCheckBtn.setOnClickListener(this)
        binding.genderMaleBtn.setOnClickListener(this)
        binding.genderFemaleBtn.setOnClickListener(this)
        binding.nextBtn.setOnClickListener(this)

        val intent = intent

        kakaoId = intent.getStringExtra("kakaoId").toString()
    }

    override fun onResume() {
        super.onResume()
        observe()

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.idCheckBtn -> {
                // 서버 로직 태움
                signupViewModel.isUserIdDuplicate(binding.nickNameET.text.toString())
            }

            R.id.genderMaleBtn , R.id.genderFemaleBtn ->{
                if(genderValue!=-1){ findViewById<Button>(genderValue).isSelected = false }
                    findViewById<Button>(v.id).isSelected = true;
                    genderValue = v.id
            }

            R.id.nextBtn ->{
                signupViewModel.signUpRequest(SignupRequest(User.userId.toString(), "dmap" , "0" , binding.nickNameET.text.toString(), genderValue ,1))
            }
        }

        nextBtnIsValid()
    }

    fun nextBtnIsValid() {
        binding.nextBtn.isSelected = nickNameCheck && genderValue!=-1
        binding.nextBtn.isEnabled = nickNameCheck && genderValue!=-1
    }

    fun observe(){
        signupViewModel.isUserIdDuplicate.observe(this, Observer {
            if(it){
                binding.nickNameETLayer.helperText = "사용가능한 아이디 입니다."
                nickNameCheck = true
            }else{
                binding.nickNameETLayer.error = "중복 된 아이디입니다."
            }
            nextBtnIsValid()
        })

        signupViewModel.singupResponseLiveData.observe(this , Observer {
            if(it){
                startActivity(Intent(this , MainActivity::class.java))
            }else{
                Toast.makeText(this , "이미 가입 된 아이디 입니다.", Toast.LENGTH_SHORT).show() }
        })

    }
}