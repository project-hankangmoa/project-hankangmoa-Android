package com.naengjjambbong.hankangmoa.Network

import com.naengjjambbong.hankangmoa.Network.Get.Response.GetCampingResponse
import com.naengjjambbong.hankangmoa.Network.Get.Response.GetMongDDangResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SeoulNetworkService {

    @GET("/{key}/json/Mgishangang/{page_no}/71/")
    fun getMongDDangList(
            @Path("key") key : String,
            @Path("page_no") pageNo : Int
    ) : Call<GetMongDDangResponse>

    @GET("/{key}/json/SebcCampingInfoKor/1/{page_no}/")
    fun getCampingList(
            @Path("key") key : String,
            @Path("page_no") pageNo : Int
    ) : Call<GetCampingResponse>
}