package com.naengjjambbong.hankangmoa.Jemin.Activity.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naengjjambbong.hankangmoa.R

class MypageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_mypage, container, false)
        // Inflate the layout for this fragmen

        return v
    }
}