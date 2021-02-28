package com.example.dmap.Login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmap.Login.network.KakaoIdResponse
import com.example.dmap.Login.network.LoginResponse
import com.example.dmap.User.User
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    val repo = LoginRepository()

    var _kakaoId = MutableLiveData<String>()
    val kakaoId : LiveData<String>
    get() = _kakaoId

    var _userLogin = MutableLiveData<Boolean>()
    val userLogin : LiveData<Boolean>
    get() = _userLogin

    fun getKakaoId(token : String){

        viewModelScope.launch {

            repo.signUpRequest(token).enqueue(object : retrofit2.Callback<KakaoIdResponse>{
                override fun onResponse(
                    call: Call<KakaoIdResponse>,
                    response: Response<KakaoIdResponse>
                ) {
                    if(response.body()!=null && response.isSuccessful){
                        _kakaoId.value = response.body()!!.id.toString()
                    }
                }

                override fun onFailure(call: Call<KakaoIdResponse>, t: Throwable) {
                    Log.e("KakaoId" , t.message.toString())
                }
            })
        }

    }

    fun userLogin(userKakaoId : String) {


        viewModelScope.launch {
            repo.userLogin(userKakaoId).enqueue(object : Callback<LoginResponse>{
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                        when(response.code()){
                            201 -> {
                                repo.user.userToken = response.body()!!.data.token
                                _userLogin.value = true
                            }
                            401 -> {
                                _userLogin.value = false
                            }
                        }

                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                }
            })
        }
    }

}