package com.naengjjambbong.hankangmoa.Gahee.Fragment

import android.support.v4.app.Fragment
import android.view.View
import com.naengjjambbong.hankangmoa.R
import kotlinx.android.synthetic.main.fragment_mypage.*

class MypageFrament :  Fragment(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v) {
            mypage_steamlist_tv -> {
                mypage_steamlist_tv.isSelected = true
                mypage_myreview_tv.isSelected = false
                //replaceFragment(MypageSteamListFragment())
            }


            mypage_myreview_tv -> {
                mypage_myreview_tv.isSelected = true
                mypage_steamlist_tv.isSelected = false
                //replaceFragment()
            }
        }
    }
}

