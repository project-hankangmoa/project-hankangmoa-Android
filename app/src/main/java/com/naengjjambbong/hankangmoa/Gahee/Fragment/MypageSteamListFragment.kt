package com.naengjjambbong.hankangmoa.Gahee.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.naengjjambbong.hankangmoa.Gahee.Adapter.MypageSteamListAdapter
import com.naengjjambbong.hankangmoa.Gahee.Item.MypageSteamListItem
import com.naengjjambbong.hankangmoa.R
import kotlinx.android.synthetic.main.fragment_mypage_steamlist.view.*

class MypageSteamListFragment : Fragment() {

    lateinit var mypageSteamListItem: ArrayList<MypageSteamListItem>
    lateinit var mypageSteamListAdapter: MypageSteamListAdapter
    lateinit var requestManager: RequestManager //RequestManger는 이미지 주소를 URL로 가져오면서 사용함


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_mypage_steamlist, container, false)
        // Inflate the layout for this fragmen

        mypageSteamListItem = ArrayList()
        mypage(v)

        return v
    }

    fun mypage(v : View){
        requestManager = Glide.with(this)//Glide는 뭐야?
        mypageSteamListItem.add(MypageSteamListItem("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png", "한강불꽃축제","2018.04.02~","잠실한강공원" ))
        mypageSteamListItem.add(MypageSteamListItem("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png", "한강아아축제","2028.04.02~","잠실한강공원" ))
        mypageSteamListItem.add(MypageSteamListItem("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png", "한강불ㅇ제","2019.04.02~","나루원" ))
        //projectItems.add(ProjectItem("https://project-cowalker.s3.ap-northeast-2.amazonaws.com/1531113346984.jpg", "ㅁㄴㅇㅎ", "ㅁㄴㅇㄹㄴㅁㅇㅎ", "ㅁㄴㅇㄹ", "ㅇㅎㅁㄴㅇㄹ"))ㅇ
        mypageSteamListAdapter = MypageSteamListAdapter(mypageSteamListItem, requestManager)
        v.mypage_steamlist_recyclerview.layoutManager = LinearLayoutManager(context!!)
        v.mypage_steamlist_recyclerview.adapter = mypageSteamListAdapter

    }
}