package com.naengjjambbong.hankangmoa.Gahee.Activity

import android.app.DownloadManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.naengjjambbong.hankangmoa.Gahee.Adapter.MypageAdapter
import com.naengjjambbong.hankangmoa.Gahee.Item.MypageItem
import com.naengjjambbong.hankangmoa.R
import kotlinx.android.synthetic.main.mypage.*

class MypageActivity : AppCompatActivity() {
    lateinit var mypageItem: ArrayList<MypageItem>
    lateinit var mypageAdapter: MypageAdapter
    lateinit var requestManager: RequestManager //RequestManger는 이미지 주소를 URL로 가져오면서 사용함

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage)

        mypageItem = ArrayList()
        mypage()
    }

    fun mypage(){
        requestManager = Glide.with(this)//Glide는 뭐야?
        mypageItem.add(MypageItem("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png", "한강불꽃축제","2018.04.02~","잠실한강공원" ))
        mypageItem.add(MypageItem("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png", "한강아아축제","2028.04.02~","잠실한강공원" ))
        mypageItem.add(MypageItem("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png", "한강불ㅇ제","2019.04.02~","나루원" ))
        //projectItems.add(ProjectItem("https://project-cowalker.s3.ap-northeast-2.amazonaws.com/1531113346984.jpg", "ㅁㄴㅇㅎ", "ㅁㄴㅇㄹㄴㅁㅇㅎ", "ㅁㄴㅇㄹ", "ㅇㅎㅁㄴㅇㄹ"))ㅇ
        mypageAdapter = MypageAdapter(mypageItem, requestManager)
        mypage_recyclerView.layoutManager = LinearLayoutManager(this)
        mypage_recyclerView.adapter = mypageAdapter
        
    }

}
