package com.naengjjambbong.hankangmoa.Jemin.Fragment

import android.annotation.TargetApi
import android.content.Intent
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.naengjjambbong.hankangmoa.Gahee.Activity.DetailActivity
import com.naengjjambbong.hankangmoa.Jemin.Activity.MainActivity
import com.naengjjambbong.hankangmoa.Jemin.Adapter.*
import com.naengjjambbong.hankangmoa.Jemin.Item.HotActivityItem
import com.naengjjambbong.hankangmoa.Jemin.Item.HotLocationItem
import com.naengjjambbong.hankangmoa.Jemin.Item.MainListItem
import com.naengjjambbong.hankangmoa.Jemin.ViewPager.CustomViewPagerAdapter
import com.naengjjambbong.hankangmoa.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(), View.OnClickListener{
    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var hotActivityItem : ArrayList<HotActivityItem>
    lateinit var mainImage : ArrayList<String>
    lateinit var hotLocationItem: ArrayList<HotLocationItem>
    lateinit var mainListItem: ArrayList<MainListItem>
    lateinit var hotActivityAdapter: HotActivityAdapter
    lateinit var hotLocationAdapter: HotLocationAdapter
    lateinit var requestManager: RequestManager //RequestManger는 이미지 주소를 URL로 가져오면서 사용함


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_home, container, false)

        requestManager = Glide.with(this)
        // Inflate the layout for this fragmen

        v.home_concert_layout.setOnClickListener {
            HomeMainFragment.homeMainFragment.replaceFragment(HomeDetailFragment())
        }
        v.home_camping_layout.setOnClickListener {
            HomeMainFragment.homeMainFragment.replaceFragment(HomeDetailFragment())
        }
        v.home_sport_layout.setOnClickListener {
            HomeMainFragment.homeMainFragment.replaceFragment(HomeDetailFragment())
        }
        v.home_flower_layout.setOnClickListener {
            HomeMainFragment.homeMainFragment.replaceFragment(HomeDetailFragment())
        }
        v.home_experience_layout.setOnClickListener {
            HomeMainFragment.homeMainFragment.replaceFragment(HomeDetailFragment())
        }
        v.home_water_layout.setOnClickListener {
            HomeMainFragment.homeMainFragment.replaceFragment(HomeDetailFragment())
        }
        v.home_exhibit_layout.setOnClickListener {
            HomeMainFragment.homeMainFragment.replaceFragment(HomeDetailFragment())
        }
        v.home_etc_layout.setOnClickListener {
            HomeMainFragment.homeMainFragment.replaceFragment(HomeDetailFragment())
        }

        getHotActivity(v)
        getHotLocation(v)
        getMainList(v)

        return v
    }

    fun getImageAdapter(v : View){
        mainImage = ArrayList()
        mainImage.add("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png")
        mainImage.add("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png")
        mainImage.add("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png")
        mainImage.add("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png")
        mainImage.add("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png")
        mainImage.add("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png")
        mainImage.add("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png")
        mainImage.add("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png")
        //var adapter = MainImageAdapter(v.context!!, requestManager, mainImage)

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getMainList(v : View){

        mainListItem = ArrayList()

        mainListItem.add(MainListItem("https://cdn.univ20.com/wp-content/uploads/2016/09/eaf418aefb7a9c97a6abeff9e7d827a0-1.jpg", "2018 한강 불꽃축제", "2018. 10. 07"))
        mainListItem.add(MainListItem("https://t1.daumcdn.net/cfile/tistory/197139465164BEBF0B", "2018 한강 봄 꽃구경", "2018. 05. 19"))
        mainListItem.add(MainListItem("https://images.kbench.com/kbench/article/2011_04/k99180p1n7.jpg", "한강 썰매 대회", "2018. 01. 11"))
        mainListItem.add(MainListItem("https://img1.daumcdn.net/thumb/S600x434/?scode=1boon&fname=https://t1.daumcdn.net/liveboard/mediaseoul/c633692445db4724984a654ae2ee8c03.JPG", "2018 한강 물총 싸움", "2018. 06. 07"))
        mainListItem.add(MainListItem("http://love.seoul.go.kr/pds/Board/seoul_news_write/201708_12_1.jpg", "2018 한강 봄꽃 축제", "2018. 03. 23"))
        mainListItem.add(MainListItem("http://image.kmib.co.kr/online_image/2016/0117/201601171739_61120010263499_1.jpg", "한강 수영장 오픈", "2018. 08. 07"))
        mainListItem.add(MainListItem("http://img.insight.co.kr/static/2018/08/11/700/42wbhmovdhjris56jo3a.jpg", "한강 캠프 파이어", "2018. 11. 27"))
        mainListItem.add(MainListItem("http://cfile221.uf.daum.net/image/990B2E425B56D2680DD609", "2018 한강 망년회", "2018. 12. 27"))

        v.home_rc_viewPager.adapter = CustomViewPagerAdapter(MainListContentAdapter(context!!, mainListItem, requestManager))
        v.home_rc_viewPager.setPadding(50, 0, 50, 0)
        v.home_rc_viewPager.pageMargin = -200
        v.home_rc_viewPager.offscreenPageLimit = 9

        v.home_rc_viewPager.setClipToPadding(false)
       // v.home_rc_viewPager.setPadding(40, 0, 40, 0)
        //v.home_rc_viewPager.setPageMargin(resources.displayMetrics.widthPixels / -9)



        val screen = Point()
        activity!!.windowManager.defaultDisplay.getSize(screen)

        val startOffset = 50.0f / (screen.x - 2 * 50.0f)
        v.home_rc_viewPager.setPageTransformer(false, CardPagerTransformerShift(v.home_rc_viewPager.elevation * 1.0f, v.home_rc_viewPager.elevation,
                0.6f, startOffset))
    }

    fun getHotActivity(v : View){

        hotActivityItem = ArrayList()

        hotActivityItem.add(HotActivityItem("https://cdn.univ20.com/wp-content/uploads/2016/09/eaf418aefb7a9c97a6abeff9e7d827a0-1.jpg", "한여름밤의 재즈","2018.08.10(금) ~ 08. 12(일)"))
        hotActivityItem.add(HotActivityItem("https://t1.daumcdn.net/cfile/tistory/197139465164BEBF0B", "한강 다리밑 영화제","2018.08.11(토) ~ 08. 15(수)"))
        hotActivityItem.add(HotActivityItem("https://images.kbench.com/kbench/article/2011_04/k99180p1n7.jpg", "한강 다리밑 영화제","2018.08.13(월) ~ 08. 17(금)"))
        hotActivityItem.add(HotActivityItem("https://img1.daumcdn.net/thumb/S600x434/?scode=1boon&fname=https://t1.daumcdn.net/liveboard/mediaseoul/c633692445db4724984a654ae2ee8c03.JPG", "한강 축제","2018.08.10(금) ~ 08. 12(일)"))
        hotActivityItem.add(HotActivityItem("http://love.seoul.go.kr/pds/Board/seoul_news_write/201708_12_1.jpg", "한강 축제","2018.08.10(금) ~ 08. 12(일)"))
        hotActivityItem.add(HotActivityItem("http://image.kmib.co.kr/online_image/2016/0117/201601171739_61120010263499_1.jpg", "한강 축제","2018.08.10(금) ~ 08. 12(일)"))
        //projectItems.add(ProjectItem("https://project-cowalker.s3.ap-northeast-2.amazonaws.com/1531113346984.jpg", "ㅁㄴㅇㅎ", "ㅁㄴㅇㄹㄴㅁㅇㅎ", "ㅁㄴㅇㄹ", "ㅇㅎㅁㄴㅇㄹ"))ㅇ
        hotActivityAdapter = HotActivityAdapter(context!!, hotActivityItem, requestManager)
        hotActivityAdapter!!.setOnItemClickListener(this@HomeFragment)
        v.home_hot_activity_recyclerview.layoutManager = GridLayoutManager(v.context, 2)
        v.home_hot_activity_recyclerview.adapter = hotActivityAdapter

    }

    fun getHotLocation(v : View){

        hotLocationItem = ArrayList()
        hotLocationItem.add(HotLocationItem("https://cdn.univ20.com/wp-content/uploads/2016/09/eaf418aefb7a9c97a6abeff9e7d827a0-1.jpg", "한여름밤의 재즈"))
        hotLocationItem.add(HotLocationItem("https://t1.daumcdn.net/cfile/tistory/197139465164BEBF0B", "한강 다리밑 영화제"))
        hotLocationItem.add(HotLocationItem("https://images.kbench.com/kbench/article/2011_04/k99180p1n7.jpg", "한강 다리밑 영화제"))
        hotLocationItem.add(HotLocationItem("https://img1.daumcdn.net/thumb/S600x434/?scode=1boon&fname=https://t1.daumcdn.net/liveboard/mediaseoul/c633692445db4724984a654ae2ee8c03.JPG", "한강 축제"))
        hotLocationItem.add(HotLocationItem("http://love.seoul.go.kr/pds/Board/seoul_news_write/201708_12_1.jpg", "한강 축제"))
        hotLocationItem.add(HotLocationItem("http://image.kmib.co.kr/online_image/2016/0117/201601171739_61120010263499_1.jpg", "한강 축제"))
        hotLocationItem.add(HotLocationItem("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png", "한강 불꽃제"))
        hotLocationItem.add(HotLocationItem("https://i.pinimg.com/originals/f7/d8/97/f7d8977935a3ef91038141f04e7e759c.png", "한강 불꽃제"))
        //projectItems.add(ProjectItem("https://project-cowalker.s3.ap-northeast-2.amazonaws.com/1531113346984.jpg", "ㅁㄴㅇㅎ", "ㅁㄴㅇㄹㄴㅁㅇㅎ", "ㅁㄴㅇㄹ", "ㅇㅎㅁㄴㅇㄹ"))ㅇ
        hotLocationAdapter = HotLocationAdapter(context!!, hotLocationItem, requestManager)
        hotLocationAdapter!!.setOnItemClickListener(this@HomeFragment)
        v.home_hot_loaction_recyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        v.home_hot_loaction_recyclerview.adapter = hotLocationAdapter

    }
}