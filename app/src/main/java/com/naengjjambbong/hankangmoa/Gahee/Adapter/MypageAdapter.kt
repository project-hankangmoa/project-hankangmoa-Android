package com.naengjjambbong.hankangmoa.Gahee.Adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.naengjjambbong.hankangmoa.Gahee.Item.MypageItem
import com.naengjjambbong.hankangmoa.Gahee.ViewHolder.MypageViewHolder
import com.naengjjambbong.hankangmoa.R

class MypageAdapter(private var mypageitems : ArrayList<MypageItem>, var requestManager : RequestManager) :
        RecyclerView.Adapter<MypageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MypageViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.mypage_recyclerview, parent, false)
        return MypageViewHolder(mainView)

    }

    override fun getItemCount(): Int = mypageitems.size

    override fun onBindViewHolder(holder: MypageViewHolder, position: Int) {
        requestManager.load(mypageitems[position].mpImageUrl).error(R.drawable.btn_heart).into(holder.mpImage)
        holder.mpName.text = mypageitems[position].mpname
        holder.mpDate.text = mypageitems[position].mpdate
        holder.mpPlace.text = mypageitems[position].mpplace
    }

}