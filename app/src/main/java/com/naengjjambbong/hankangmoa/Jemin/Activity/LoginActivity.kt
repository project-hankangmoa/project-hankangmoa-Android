package com.naengjjambbong.hankangmoa.Jemin.Activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.naengjjambbong.hankangmoa.Network.ApplicationController
import com.naengjjambbong.hankangmoa.Network.NetworkService
import com.naengjjambbong.hankangmoa.Network.Post.PostPwCheckResponse
import com.naengjjambbong.hankangmoa.R
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    var emailValue : String = ""
    var pwValue : String = ""

    lateinit var networkService : NetworkService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_login_btn.setOnClickListener {

            emailValue = login_email_edit.text.toString()
            pwValue = login_passwd_edit.text.toString()
            postPwCheck()

        }

        login_register_tv.setOnClickListener {
            var intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    fun postPwCheck() {
        networkService = ApplicationController.getRetrofit().create(NetworkService::class.java)

        val email = RequestBody.create(MediaType.parse("text.plain"), emailValue)
        val pw = RequestBody.create(MediaType.parse("text.plain"), pwValue)

        val postPwCheckResponse = networkService.postPwCheck(email, pw)

        postPwCheckResponse.enqueue(object : retrofit2.Callback<PostPwCheckResponse>{

            override fun onResponse(call: Call<PostPwCheckResponse>, response: Response<PostPwCheckResponse>) {
                Log.v("TAG", "패스워드 체크 통신 성공")
                if(response.isSuccessful){
                    if(response.body()!!.code == "OK"){
                        var intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(applicationContext, "입력 정보가 틀렸습니다.", Toast.LENGTH_SHORT).show()
                    }
                    //Log.v("adsf", "패스워드 체크 전달 성공 = " + response.body()!!.code)

                }
                else{

                    Log.v("TAG", "패스워드 체크 전달 실패"+ response!!.body().toString())
                }
            }

            override fun onFailure(call: Call<PostPwCheckResponse>, t: Throwable?) {
                Toast.makeText(applicationContext,"패스워드 체크 서버 연결 실패", Toast.LENGTH_SHORT).show()
            }

        })
    }


}
