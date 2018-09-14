package com.naengjjambbong.hankangmoa.Jemin.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.naengjjambbong.hankangmoa.Jemin.Item.LinearPhotoItem
import com.naengjjambbong.hankangmoa.Jemin.ViewHolder.LinearPhotoViewHolder
import com.naengjjambbong.hankangmoa.R

class LinearPhotoAdapter(private var linearPhotoItem : ArrayList<LinearPhotoItem>, var requestManager : RequestManager) : RecyclerView.Adapter<LinearPhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinearPhotoViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_linear_photo, parent, false)
        return LinearPhotoViewHolder(mainView)

    }

    override fun getItemCount(): Int = linearPhotoItem.size

    override fun onBindViewHolder(holder: LinearPhotoViewHolder, position: Int) {
        requestManager.load(linearPhotoItem[position].linearPhotoImageUrl).into(holder.linearPhotoImage)
        requestManager.load(linearPhotoItem[position].linearDetailContentImageUrl).into(holder.linearDetailContentImageUrl)
        requestManager.load(linearPhotoItem[position].linearPhotoMakerImageUrl).into(holder.linearPhotoMakerImageUrl)
        //requestManager.load(hotActivityItem[position].HotActivityImageUrl).error(R.drawable.btn_heart).into(holder.hotActivityImage)

        if(linearPhotoItem[position].linearDetailContentName == null || linearPhotoItem[position].linearDetailContentRange == null || linearPhotoItem[position].linearContentAddress==null){
            holder.linearSmallContentAddress.text = linearPhotoItem[position].linearContentAddress
            holder.linearDetailLayout.visibility = View.GONE
            holder.linearSmallLayout.visibility = View.VISIBLE
            holder.linearTrashLayout.visibility = View.GONE
        }

        else{
            holder.linearDetailContentName.text = linearPhotoItem[position].linearDetailContentName
            holder.linearDetailContentRange.text = linearPhotoItem[position].linearDetailContentRange
            holder.linearDetailContentAddress.text = linearPhotoItem[position].linearContentAddress

            holder.linearDetailLayout.visibility = View.VISIBLE
            holder.linearSmallLayout.visibility = View.GONE
            holder.linearTrashLayout.visibility = View.VISIBLE

        }
        holder.linearPhotoMakerName.text = linearPhotoItem[position].linearPhotoMakerName
        holder.linearPhotoHeartCount.text = linearPhotoItem[position].linearPhotoHearCount.toString()
        holder.linearPhotoReview.text = linearPhotoItem[position].linearPhotoReview




    }

}