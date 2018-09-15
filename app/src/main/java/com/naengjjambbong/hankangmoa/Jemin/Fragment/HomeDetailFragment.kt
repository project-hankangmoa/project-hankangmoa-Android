package com.naengjjambbong.hankangmoa.Jemin.Fragment

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.naengjjambbong.hankangmoa.Gahee.Activity.DetailActivity
import com.naengjjambbong.hankangmoa.Jemin.Activity.MainActivity
import com.naengjjambbong.hankangmoa.Jemin.Adapter.HomeDetailAdapter
import com.naengjjambbong.hankangmoa.Jemin.Item.HomeDetailItem
import com.naengjjambbong.hankangmoa.Jemin.Item.HotActivityItem
import com.naengjjambbong.hankangmoa.R
import kotlinx.android.synthetic.main.fragment_home_detail.*
import kotlinx.android.synthetic.main.fragment_home_detail.view.*
import kotlinx.android.synthetic.main.fragment_photo.view.*

class HomeDetailFragment : Fragment(), MainActivity.OnBackPressedListener, View.OnClickListener{

    var homeDetailItem = ArrayList<HomeDetailItem>()
    lateinit var homeDetailAdapter : HomeDetailAdapter
    lateinit var requestManager: RequestManager
    var flag : Int = 0

    override fun onClick(v: View?) {
        val idx : Int = home_detail_recyclerview.getChildAdapterPosition(v)
        Log.v("TAG","클릭이벤트 감지 포지션 = " + idx)

        val intent = Intent(getActivity(), DetailActivity::class.java)
        startActivity(intent)
    }

    var mainFragment = HomeFragment()
    override fun onBack() {
        Log.e("Other", "onBack()")
        // 리스너를 설정하기 위해 Activity 를 받아옵니다.
        //val activity = activity as MainActivity?
        // 한번 뒤로가기 버튼을 눌렀다면 Listener 를 null 로 해제해줍니다.
        MainActivity.mainActivity.setOnBackPressedListener(null)
        // MainFragment 로 교체
        HomeMainFragment.homeMainFragment.replaceFragment(mainFragment)
        // Activity 에서도 뭔가 처리하고 싶은 내용이 있다면 하단 문장처럼 호출해주면 됩니다.
        // activity.onBackPressed();
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_home_detail, container, false)
        // Inflate the layout for this fragmen

        val category_list = arrayOf("문화/전시", "음악/콘서트", "캠핑", "스포츠", "꽃놀이", "체험", "물놀이", "기타")
        val sort_list = arrayOf("최신순", "인기순")

        val categorySpinner = v.findViewById<View>(R.id.home_detail_category_spinner) as Spinner
        val sortSpinner = v.findViewById<View>(R.id.home_detail_sort_spinner) as Spinner

        // 카테고리에 대한 Spinner
        val adapter = ArrayAdapter(
                activity, // 현재화면의 제어권자
                R.layout.spin,
                category_list)
        adapter.setDropDownViewResource(
                R.layout.spin_dropdown)
        categorySpinner.adapter = adapter

        // 정렬에 대한 Spinner
        val adapter2 = ArrayAdapter(
                activity, // 현재화면의 제어권자
                R.layout.spin,
                sort_list)
        adapter2.setDropDownViewResource(
                R.layout.spin_dropdown)
        sortSpinner.adapter = adapter2

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position == 0) {

                } else if (position == 1) {

                } else if (position == 2) {

                } else if (position == 3) {

                } else if (position == 4) {

                } else if (position == 5) {

                } else if(position == 6) {

                } else {

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position == 0) {

                } else {

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        v.home_detail_proceeding_btn.isSelected = true
        v.home_detail_proceeding_btn.setTextColor(Color.parseColor("#ffffff"))
        requestManager = Glide.with(this)
        getHomeDetailList(v)

        v.home_detail_proceeding_btn.setOnClickListener {
            flag = 0
            v.home_detail_proceeding_btn.isSelected = true
            v.home_detail_proceeding_btn.setTextColor(Color.parseColor("#ffffff"))
            v.home_detail_scheduled_btn.isSelected = false
            v.home_detail_scheduled_btn.setTextColor(Color.parseColor("#000000"))
            v.home_detail_completed_btn.isSelected = false
            v.home_detail_completed_btn.setTextColor(Color.parseColor("#000000"))
            getHomeDetailList(v)
        }

        v.home_detail_scheduled_btn.setOnClickListener {
            flag = 1
            v.home_detail_scheduled_btn.isSelected = true
            v.home_detail_scheduled_btn.setTextColor(Color.parseColor("#ffffff"))
            v.home_detail_proceeding_btn.isSelected = false
            v.home_detail_proceeding_btn.setTextColor(Color.parseColor("#000000"))
            v.home_detail_completed_btn.isSelected = false
            v.home_detail_completed_btn.setTextColor(Color.parseColor("#000000"))
            getHomeDetailList(v)
        }

        v.home_detail_completed_btn.setOnClickListener {
            flag = 2
            v.home_detail_completed_btn.isSelected = true
            v.home_detail_completed_btn.setTextColor(Color.parseColor("#ffffff"))
            v.home_detail_proceeding_btn.isSelected = false
            v.home_detail_proceeding_btn.setTextColor(Color.parseColor("#000000"))
            v.home_detail_scheduled_btn.isSelected = false
            v.home_detail_scheduled_btn.setTextColor(Color.parseColor("#000000"))
            getHomeDetailList(v)
        }



        return v


    }

    // Fragment 호출 시 반드시 호출되는 오버라이드 메소드입니다.
    //                     혹시 Context 로 안되시는분은 Activity 로 바꿔보시기 바랍니다.
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("Other", "onAttach()")
        (context as MainActivity).setOnBackPressedListener(this)
    }



    fun getHomeDetailList(v : View){

        if(flag==0){
            homeDetailItem.clear()
            homeDetailItem.add(HomeDetailItem("https://cdn.univ20.com/wp-content/uploads/2016/09/eaf418aefb7a9c97a6abeff9e7d827a0-1.jpg", "한여름밤의 재즈", "2018.08.10(금) ~ 08. 12(일)", 5.0))
            homeDetailItem.add(HomeDetailItem("https://t1.daumcdn.net/cfile/tistory/197139465164BEBF0B", "한강 다리밑 영화제","2018.08.11(토) ~ 08. 15(수)", 5.0))
            homeDetailItem.add(HomeDetailItem("https://images.kbench.com/kbench/article/2011_04/k99180p1n7.jpg", "한강 다리밑 영화제","2018.08.13(월) ~ 08. 17(금)", 5.0))
            homeDetailItem.add(HomeDetailItem("https://img1.daumcdn.net/thumb/S600x434/?scode=1boon&fname=https://t1.daumcdn.net/liveboard/mediaseoul/c633692445db4724984a654ae2ee8c03.JPG", "한강 축제","2018.08.10(금) ~ 08. 12(일)", 5.0))
            homeDetailItem.add(HomeDetailItem("http://love.seoul.go.kr/pds/Board/seoul_news_write/201708_12_1.jpg", "한강 축제","2018.08.10(금) ~ 08. 12(일)", 5.0))
            homeDetailItem.add(HomeDetailItem("http://image.kmib.co.kr/online_image/2016/0117/201601171739_61120010263499_1.jpg", "한강 축제","2018.08.10(금) ~ 08. 12(일)", 5.0))
        }
        else if(flag==1){
            homeDetailItem.clear()
            homeDetailItem.add(HomeDetailItem("https://t1.daumcdn.net/cfile/tistory/197139465164BEBF0B", "한강 다리밑 영화제","2018.08.11(토) ~ 08. 15(수)", 5.0))
            homeDetailItem.add(HomeDetailItem("https://images.kbench.com/kbench/article/2011_04/k99180p1n7.jpg", "한강 다리밑 영화제","2018.08.13(월) ~ 08. 17(금)", 5.0))
            homeDetailItem.add(HomeDetailItem("https://img1.daumcdn.net/thumb/S600x434/?scode=1boon&fname=https://t1.daumcdn.net/liveboard/mediaseoul/c633692445db4724984a654ae2ee8c03.JPG", "한강 축제","2018.08.10(금) ~ 08. 12(일)", 5.0))
            homeDetailItem.add(HomeDetailItem("https://cdn.univ20.com/wp-content/uploads/2016/09/eaf418aefb7a9c97a6abeff9e7d827a0-1.jpg", "한여름밤의 재즈", "2018.08.10(금) ~ 08. 12(일)", 5.0))
            homeDetailItem.add(HomeDetailItem("http://image.kmib.co.kr/online_image/2016/0117/201601171739_61120010263499_1.jpg", "한강 축제","2018.08.10(금) ~ 08. 12(일)", 5.0))

        }
        else if(flag==2){
            homeDetailItem.clear()
            homeDetailItem.add(HomeDetailItem("https://img1.daumcdn.net/thumb/S600x434/?scode=1boon&fname=https://t1.daumcdn.net/liveboard/mediaseoul/c633692445db4724984a654ae2ee8c03.JPG", "한강 축제","2018.08.10(금) ~ 08. 12(일)", 5.0))
            homeDetailItem.add(HomeDetailItem("https://cdn.univ20.com/wp-content/uploads/2016/09/eaf418aefb7a9c97a6abeff9e7d827a0-1.jpg", "한여름밤의 재즈", "2018.08.10(금) ~ 08. 12(일)", 5.0))
            homeDetailItem.add(HomeDetailItem("http://image.kmib.co.kr/online_image/2016/0117/201601171739_61120010263499_1.jpg", "한강 축제","2018.08.10(금) ~ 08. 12(일)", 5.0))
            homeDetailItem.add(HomeDetailItem("https://t1.daumcdn.net/cfile/tistory/197139465164BEBF0B", "한강 다리밑 영화제","2018.08.11(토) ~ 08. 15(수)", 5.0))
            homeDetailItem.add(HomeDetailItem("https://images.kbench.com/kbench/article/2011_04/k99180p1n7.jpg", "한강 다리밑 영화제","2018.08.13(월) ~ 08. 17(금)", 5.0))
        }


        homeDetailAdapter = HomeDetailAdapter(context!!, homeDetailItem, requestManager)
        homeDetailAdapter.setOnItemClickListener(this@HomeDetailFragment)
        v.home_detail_recyclerview.layoutManager = LinearLayoutManager(v.context)
        v.home_detail_recyclerview.adapter = homeDetailAdapter

    }



}
