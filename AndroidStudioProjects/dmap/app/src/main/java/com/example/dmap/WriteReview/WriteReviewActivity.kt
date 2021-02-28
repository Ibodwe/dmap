package com.example.dmap.WriteReview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.example.dmap.R
import com.example.dmap.User.User
import com.example.dmap.WriteReview.data.WriteReviewRequest
import com.example.dmap.databinding.ActivityLoginBinding
import com.example.dmap.databinding.ActivityWritreReviewBinding
import java.util.*

class WriteReviewActivity : AppCompatActivity() {

    lateinit var binding : ActivityWritreReviewBinding
    var lat : Double = -1.0
    var lon : Double = -1.0
    var toiletId = ""
    val writeReviewViewModel : WriteReviewViewModel by lazy{
        WriteReviewViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWritreReviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initSpinnser()
        ObserveData()

        val intent = intent
        lat = intent.getDoubleExtra("toiletLat" , -1.0)
        lon = intent.getDoubleExtra("toiletLon" , -1.0)
        toiletId = intent.getStringExtra("toiletId").toString()

        binding.nextBtn.setOnClickListener {
            checkReviewAllFiled()
        }
    }

    fun initSpinnser(){
        binding.toiletYear.value = 2000
        binding.toiletYear.minValue = 1980
        binding.toiletYear.maxValue = 2100
    }

    fun ObserveData(){

        writeReviewViewModel.registerReviewMessage.observe(this, Observer {
            if(it){
                Toast.makeText(this , "리뷰 등록에 성공하였습니다." , Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this , "일시적인 오류입니다. 다음에 시도해 주세요." , Toast.LENGTH_SHORT).show()
            }

        })
    }


    fun checkReviewAllFiled() {
        Log.d("cleanOftoilet" , binding.clearRadioGroup.checkedRadioButtonId.toString())
        Log.d("amountOfTissue" , binding.tissueRadioGroup.checkedRadioButtonId .toString())
        Log.d("isSecret" ,  binding.passwordRadioGroup.checkedRadioButtonId.toString())

        if(!binding.titleET.text.isNotEmpty()){
            Toast.makeText(this , "리뷰 제목을 입력해주세요." , Toast.LENGTH_SHORT).show()
        }else if (binding.genderRadioGroup.checkedRadioButtonId== -1){
            Toast.makeText(this , "성별을 선택해주세요." , Toast.LENGTH_SHORT).show()
        }else if (binding.clearRadioGroup.checkedRadioButtonId == -1){
            Toast.makeText(this , "깨끗함의 정도를 선택해주세요." , Toast.LENGTH_SHORT).show()
        }else if (binding.tissueRadioGroup.checkedRadioButtonId == -1){
            Toast.makeText(this , "휴지의 양을 선택해주세요." , Toast.LENGTH_SHORT).show()
        }else if (binding.passwordRadioGroup.checkedRadioButtonId == -1){
            Toast.makeText(this , "비밀번호 여부를 선택해주세요." , Toast.LENGTH_SHORT).show()
        }else if(!binding.shortReview.text.isNotEmpty()){
            Toast.makeText(this , "한줄 평을 작성해 주세요." , Toast.LENGTH_SHORT).show()
        }else{
            writeReviewViewModel.registerReview(WriteReviewRequest(
                amount_of_tissue = binding.tissueRadioGroup.checkedRadioButtonId-7 ,
                clean_of_toilet = binding.clearRadioGroup.checkedRadioButtonId-2,
                id = UUID.randomUUID().toString(),
                image = null,
                is_old = 100 - (Calendar.getInstance().get(Calendar.YEAR) - binding.toiletYear.value),
                is_secret = binding.passwordRadioGroup.checkedRadioButtonId-12 ,
                latitude = lat,
                longitude = lon,
                sex = binding.genderRadioGroup.checkedRadioButtonId,
                shot_detail = binding.shortReview.text.toString(),
                title = binding.titleET.text.toString(),
                toiletId = toiletId ,
                userId = User.userId ?: "temp"
            ))
        }


    }

}