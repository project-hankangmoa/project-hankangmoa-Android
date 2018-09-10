package com.naengjjambbong.hankangmoa.Jemin.Adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.naengjjambbong.hankangmoa.Gahee.Item.MypageItem
import com.naengjjambbong.hankangmoa.Gahee.ViewHolder.MypageViewHolder
import com.naengjjambbong.hankangmoa.Jemin.Item.HotActivityItem
import com.naengjjambbong.hankangmoa.Jemin.ViewHolder.HotActivityViewHolder
import com.naengjjambbong.hankangmoa.R

class HotActivityAdapter(private var hotActivityItem : ArrayList<HotActivityItem>, var requestManager : RequestManager) : RecyclerView.Adapter<HotActivityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotActivityViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_hot_activity, parent, false)
        return HotActivityViewHolder(mainView)

    }

    override fun getItemCount(): Int = hotActivityItem.size

    override fun onBindViewHolder(holder: HotActivityViewHolder, position: Int) {
        requestManager.load(hotActivityItem[position].HotActivityImageUrl).into(holder.hotActivityImage)
        //requestManager.load(hotActivityItem[position].HotActivityImageUrl).error(R.drawable.btn_heart).into(holder.hotActivityImage)
        holder.hotActivityName.text = hotActivityItem[position].HotActivityName
        holder.hotDateRange.text = hotActivityItem[position].HotActivityDateRange
    }

}