package com.naengjjambbong.hankangmoa.Gahee.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.naengjjambbong.hankangmoa.Gahee.Activity.ReviewWritingActivity
import com.naengjjambbong.hankangmoa.Gahee.Adapter.ReviewAdapter
import com.naengjjambbong.hankangmoa.Gahee.Item.ReviewItem
import com.naengjjambbong.hankangmoa.R
import kotlinx.android.synthetic.main.activity_review.*
import kotlinx.android.synthetic.main.fragment_mypage_myreview.*
import kotlinx.android.synthetic.main.fragment_mypage_myreview.view.*

class MypageMyreviewFragment : Fragment() {

    lateinit var reviewItem: ArrayList<ReviewItem>
    lateinit var reviewAdapter: ReviewAdapter
    lateinit var requestManager: RequestManager //RequestManger는 이미지 주소를 URL로 가져오면서 사용함


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_mypage_myreview, container, false)
        // Inflate the layout for this fragmen

        review(v)
        return v
    }

    fun review(v : View) {
        requestManager = Glide.with(this)
        reviewItem = ArrayList()
        reviewItem.add(ReviewItem("한강몽땅 종이배 경주대회","","김지혜", "시적 순간의 생명력으로 재창조했다. 그만의 고유한 방식으로 촬영된 ‘필름다이어리’ 작품들은 지나간 시간들에 살아 움직이는 이미지의 숨결을 불어넣는다. 덧없이 찰나...","","2017.12.29" ))
        reviewItem.add(ReviewItem("한강 불꽃 축제","","이재란", "시적 순간의 생명력으로 재창조했다. 그만의 고유한 방식으로 촬영된 ‘필름다이어리’ 작품들은 지나간 시간들에 살아 움직이는 이미지의 숨결을 불어넣는다. 덧없이 찰나...","","2017.12.23" ))
        reviewItem.add(ReviewItem("어벤져스","","유가희", "시적 순간의 생명력으로 재창조했다. 그만의 고유한 방식으로 촬영된 ‘필름다이어리’ 작품들은 지나간 시간들에 살아 움직이는 이미지의 숨결을 불어넣는다. 덧없이 찰나...","","2017.12.29" ))

        //projectItems.add(ProjectItem("https://project-cowalker.s3.ap-northeast-2.amazonaws.com/1531113346984.jpg", "ㅁㄴㅇㅎ", "ㅁㄴㅇㄹㄴㅁㅇㅎ", "ㅁㄴㅇㄹ", "ㅇㅎㅁㄴㅇㄹ"))ㅇ
        reviewAdapter = ReviewAdapter(reviewItem, requestManager)

        v.mypage_myreview_recyclerview.layoutManager = LinearLayoutManager(context)
        v.mypage_myreview_recyclerview.adapter = reviewAdapter

    }
}