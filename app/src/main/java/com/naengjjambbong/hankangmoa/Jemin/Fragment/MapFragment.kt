package com.naengjjambbong.hankangmoa.Jemin.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naengjjambbong.hankangmoa.R

class MapFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_home_detail, container, false)
        // Inflate the layout for this fragmen

        return v
    }
}