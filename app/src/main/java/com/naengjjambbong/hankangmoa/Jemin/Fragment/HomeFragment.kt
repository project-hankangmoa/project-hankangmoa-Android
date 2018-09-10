package com.naengjjambbong.hankangmoa.Jemin.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.naengjjambbong.hankangmoa.Gahee.Adapter.MypageAdapter
import com.naengjjambbong.hankangmoa.Gahee.Item.MypageItem
import com.naengjjambbong.hankangmoa.Jemin.Adapter.HotActivityAdapter
import com.naengjjambbong.hankangmoa.Jemin.Adapter.HotLocationAdapter
import com.naengjjambbong.hankangmoa.Jemin.Item.HotActivityItem
import com.naengjjambbong.hankangmoa.Jemin.Item.HotLocationItem
import com.naengjjambbong.hankangmoa.R
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_mypage.view.*

class HomeFragment : Fragment() {

    lateinit var hotActivityItem : ArrayList<HotActivityItem>
    lateinit var hotLocationItem: ArrayList<HotLocationItem>
    lateinit var hotActivityAdapter: HotActivityAdapter
    lateinit var hotLocationAdapter: HotLocationAdapter
    lateinit var requestManager: RequestManager //RequestManger는 이미지 주소를 URL로 가져오면서 사용함

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_home, container, false)

        requestManager = Glide.with(this)
        // Inflate the layout for this fragmen

        getHotActivity(v)
        getHotLocation(v)

        return v
    }

    fun getHotActivity(v : View){

        hotActivityItem = ArrayList()

        hotActivityItem.add(HotActivityItem("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png", "한여름밤의 재즈","2018.08.10(금) ~ 08. 12(일)"))
        hotActivityItem.add(HotActivityItem("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png", "한강 다리밑 영화제","2018.08.11(토) ~ 08. 15(수)"))
        hotActivityItem.add(HotActivityItem("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png", "한강 다리밑 영화제","2018.08.13(월) ~ 08. 17(금)"))
        hotActivityItem.add(HotActivityItem("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png", "한강 축제","2018.08.10(금) ~ 08. 12(일)"))
        //projectItems.add(ProjectItem("https://project-cowalker.s3.ap-northeast-2.amazonaws.com/1531113346984.jpg", "ㅁㄴㅇㅎ", "ㅁㄴㅇㄹㄴㅁㅇㅎ", "ㅁㄴㅇㄹ", "ㅇㅎㅁㄴㅇㄹ"))ㅇ
        hotActivityAdapter = HotActivityAdapter(hotActivityItem, requestManager)
        v.home_hot_activity_recyclerview.layoutManager = GridLayoutManager(v.context, 2)
        v.home_hot_activity_recyclerview.adapter = hotActivityAdapter

    }

    fun getHotLocation(v : View){

        hotLocationItem = ArrayList()
        hotLocationItem.add(HotLocationItem("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png", "여의도 한강공원"))
        hotLocationItem.add(HotLocationItem("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png", "서래섬 유채꽃 축제"))
        hotLocationItem.add(HotLocationItem("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png", "한강 불꽃제"))
        //projectItems.add(ProjectItem("https://project-cowalker.s3.ap-northeast-2.amazonaws.com/1531113346984.jpg", "ㅁㄴㅇㅎ", "ㅁㄴㅇㄹㄴㅁㅇㅎ", "ㅁㄴㅇㄹ", "ㅇㅎㅁㄴㅇㄹ"))ㅇ
        hotLocationAdapter = HotLocationAdapter(hotLocationItem, requestManager)
        v.home_hot_loaction_recyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        v.home_hot_loaction_recyclerview.adapter = hotLocationAdapter

    }
}