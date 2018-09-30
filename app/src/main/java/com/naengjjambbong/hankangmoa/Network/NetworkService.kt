package com.naengjjambbong.hankangmoa.Network

import com.naengjjambbong.hankangmoa.Network.Get.Response.GetImageSearchResponse
import com.naengjjambbong.hankangmoa.Network.Item.PostRegister
import com.naengjjambbong.hankangmoa.Network.Post.*
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

    @Multipart
    @POST("hangangmoa/pwcheck")
    fun postPwCheck(
            @Part("email") email : RequestBody,
            @Part("pw") pw : RequestBody
    ) : Call<PostPwCheckResponse>

    @Multipart
    @POST("hangangmoa/upload/user/{idx}")
    fun postProfileImage(
            @Path("idx") userIdx : String,
            @Part("idx") idx : RequestBody,
            @Part("file") file : MultipartBody.Part?
    ) : Call<PostProfileImageResponse>

    @Multipart
    @POST("hangangmoa/insertreview")
    fun postReviewWrite(
            @Part("idx") userIdx : RequestBody,
            @Part("rvList") rvList : RequestBody,
            @Part("rvImage") rvImage : MultipartBody.Part?,
            @Part("rvText") rvText : RequestBody
    ) : Call<PostReviewWriteResponse>

    @Multipart
    @POST("hangangmoa/insertlikelist")
    fun postMyDibs(
            @Part("idx") idx : RequestBody,
            @Part("listName") listName : RequestBody,
            @Part("listGrade") listGrade : RequestBody,
            @Part("listDate") listDate : RequestBody,
            @Part("listTime") listTime : RequestBody,
            @Part("listPlace") listPlace : RequestBody,
            @Part("listInfo") listInfo : RequestBody,
            @Part("imageURL") imageURL : MultipartBody.Part?
    ) : Call<PostMyDibsResponse>



}