package com.naengjjambbong.hankangmoa.Gahee.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.naengjjambbong.hankangmoa.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detail_activity_review_img.setOnClickListener{
            var intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)

        }

    }
}
