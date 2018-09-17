package com.naengjjambbong.hankangmoa.Gahee.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.naengjjambbong.hankangmoa.Jemin.Adapter.PhotoCategoryAdapter
import com.naengjjambbong.hankangmoa.Jemin.Item.PhotoCategoryItem
import com.naengjjambbong.hankangmoa.R
import kotlinx.android.synthetic.main.fragment_mypage.*
import kotlinx.android.synthetic.main.fragment_mypage.view.*
import kotlinx.android.synthetic.main.fragment_photo.view.*

class MypageFragment :  Fragment(), View.OnClickListener {

    lateinit var requestManager : RequestManager

    override fun onClick(v: View?) {
        when (v) {
            mypage_steamlist_tv -> {
                mypage_steamlist_tv.isSelected = true
                mypage_myreview_tv.isSelected = false
                replaceFragment(MypageSteamListFragment())
            }


            mypage_myreview_tv -> {
                mypage_myreview_tv.isSelected = true
                mypage_steamlist_tv.isSelected = false
                replaceFragment(MypageMyreviewFragment())
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_mypage, container, false)
        // Inflate the layout for this fragmen

        requestManager = Glide.with(this)

        v.mypage_steamlist_tv.setOnClickListener(this)
        v.mypage_myreview_tv.setOnClickListener(this)

        v.mypage_steamlist_tv.isSelected = true

        addFragment(MypageSteamListFragment())
        return v
    }

    fun addFragment(fragment : Fragment){
        val fm = childFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.fragmnet_mypage_content_layout, fragment)
        transaction.commit()
    }

    fun replaceFragment(fragment: Fragment)
    {
        val fm = childFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.fragmnet_mypage_content_layout, fragment)
//        transaction.addToBackStack(null)
        transaction.commit()
    }

}