package com.naengjjambbong.hankangmoa.Network

import com.naengjjambbong.hankangmoa.Network.Get.Response.GetImageSearchResponse
import com.naengjjambbong.hankangmoa.Network.Item.PostRegister
import com.naengjjambbong.hankangmoa.Network.Post.PostRegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {

    @POST("hangangmoa/join")
    fun postRegister(
            @Body postRegister : PostRegister
    ) : Call<PostRegisterResponse>

    @GET("hangangmoa/join")
    fun postRegis2ter(
            @Header("Authorization") Authorization: String,
            @Query("query") query : String
    ) : Call<GetImageSearchResponse>

}