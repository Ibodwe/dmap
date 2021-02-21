package com.example.dmap.Signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dmap.Map.network.GetToiletByLocationResponse
import com.example.dmap.Signup.network.SignupRequest
import com.example.dmap.Signup.network.SignupResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception
import javax.security.auth.callback.Callback

//signUp interface
class SignUpViewModel() : ViewModel() {

    val repo = SignupRepository()

    var _signupResponeLiveData = MutableLiveData<Boolean>()
    val singupResponseLiveData: LiveData<Boolean>
        get() = _signupResponeLiveData

    var _isUserIdDuplicate = MutableLiveData<Boolean>()
    val isUserIdDuplicate: LiveData<Boolean>
        get() = _isUserIdDuplicate


    fun signUpRequest(body: SignupRequest) {

        viewModelScope.launch {

            repo.signUpRequest(body).enqueue(object : retrofit2.Callback<SignupResponse> {
                override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                    _signupResponeLiveData.value = false
                }

                override fun onResponse(
                    call: Call<SignupResponse>,
                    response: Response<SignupResponse>
                ) {

                    when (response.code()) {
                        201 -> _signupResponeLiveData.value = true
                        401 -> _signupResponeLiveData.value = false
                        else -> { _signupResponeLiveData.value = false }
                    }

                }
            })

        }
    }

    fun isUserIdDuplicate(nickName : String) {

        viewModelScope.launch {
            repo.isUserIdDuplicate(nickName).enqueue(object : retrofit2.Callback<SignupResponse>{
                override fun onResponse(
                    call: Call<SignupResponse>,
                    response: Response<SignupResponse>
                ) {

                    when(response.code()){
                        200 -> { _isUserIdDuplicate.value = true}
                        400 -> { _isUserIdDuplicate.value = false}
                    }

                }

                override fun onFailure(call: Call<SignupResponse>, t: Throwable) {

                }
            })
        }
    }



}