package com.example.dmap.Signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.dmap.Map.MainActivity
import com.example.dmap.R
import com.example.dmap.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() , View.OnClickListener {

    lateinit var binding : ActivitySignUpBinding
    private var genderValue = -1;
    private var nickNameCheck = false
    private var genderButtons = mutableListOf(R.id.genderMaleBtn , R.id.genderFemaleBtn)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.idCheckBtn.setOnClickListener(this)
        binding.genderMaleBtn.setOnClickListener(this)
        binding.genderFemaleBtn.setOnClickListener(this)
        binding.nextBtn.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.idCheckBtn -> {
                // 서버 로직 태움
                 nickNameCheck = true
                if(nickNameCheck){
                    binding.nickNameETLayer.helperText = "사용가능한 아이디 입니다."
                }else{
                    binding.nickNameETLayer.error = "중복 된 아이디입니다."
                }
            }

            R.id.genderMaleBtn , R.id.genderFemaleBtn ->{
                if(genderValue!=-1){
                    findViewById<Button>(genderValue).isSelected = false
                }
                    findViewById<Button>(v.id).isSelected = true;
                    genderValue = v.id
            }
            R.id.nextBtn ->{
                startActivity(Intent(this , MainActivity::class.java))
            }
        }

        nextBtnIsValid()
    }

    fun nextBtnIsValid() {
        binding.nextBtn.isSelected = nickNameCheck && genderValue!=-1
        binding.nextBtn.isEnabled = nickNameCheck && genderValue!=-1
    }
}