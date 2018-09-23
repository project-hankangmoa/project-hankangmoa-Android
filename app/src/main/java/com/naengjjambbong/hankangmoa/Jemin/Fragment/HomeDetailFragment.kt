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
import com.naengjjambbong.hankangmoa.Jemin.Item.MongDDangItem
import com.naengjjambbong.hankangmoa.Network.Get.GetMongDDangMesssage
import com.naengjjambbong.hankangmoa.Network.Get.Response.GetImageSearchResponse
import com.naengjjambbong.hankangmoa.Network.Get.Response.GetMongDDangResponse
import com.naengjjambbong.hankangmoa.Network.Get.Response.GetMongDDangRowData
import com.naengjjambbong.hankangmoa.Network.RestApplicationController
import com.naengjjambbong.hankangmoa.Network.RestNetworkService
import com.naengjjambbong.hankangmoa.Network.SeoulApiController
import com.naengjjambbong.hankangmoa.Network.SeoulNetworkService
import com.naengjjambbong.hankangmoa.R
import kotlinx.android.synthetic.main.fragment_home_detail.*
import kotlinx.android.synthetic.main.fragment_home_detail.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class HomeDetailFragment : Fragment(), MainActivity.OnBackPressedListener, View.OnClickListener{

    lateinit var seoulNetworkService : SeoulNetworkService
    lateinit var restNetworkService : RestNetworkService
    lateinit var mongDDangRow : GetMongDDangMesssage
    lateinit var mongDDangData : ArrayList<GetMongDDangRowData>
    var pageNo : Int = 0
    var timeFlag : Int = 0

    var checkFlag : Int = 0

    var homeDetailItem = ArrayList<HomeDetailItem>()
    var mongDDangItem = ArrayList<MongDDangItem>()

    var selectedDay: String = ""

    var startMonth : String = ""
    var startDay : String = ""
    var startDate: String = ""

    var finishMonth : String = ""
    var finishDay : String = ""
    var finishDate: String = ""

    var mongDDangProceedingItem = ArrayList<MongDDangItem>()
    var mongDDangScheduledItem = ArrayList<MongDDangItem>()
    var mongDDangCompletedItem = ArrayList<MongDDangItem>()

    lateinit var homeDetailAdapter : HomeDetailAdapter
    lateinit var requestManager: RequestManager
    var flag : Int = 0
    var dateFlag : Int = 1

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

        mongDDangData = ArrayList()
        mongDDangItem = ArrayList()

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
        //getHomeDetailList(v)
        getMongDDangList(v)

        v.home_detail_proceeding_btn.setOnClickListener {

            timeFlag = 0
            v.home_detail_proceeding_btn.isSelected = true
            v.home_detail_proceeding_btn.setTextColor(Color.parseColor("#ffffff"))
            v.home_detail_scheduled_btn.isSelected = false
            v.home_detail_scheduled_btn.setTextColor(Color.parseColor("#000000"))
            v.home_detail_completed_btn.isSelected = false
            v.home_detail_completed_btn.setTextColor(Color.parseColor("#000000"))
            //getHomeDetailList(v)
            while(checkFlag == 0){
                Log.v("asdf","리스트 받아오는중")

            }
            Log.v("asdf","리스트 다 받아옴")
            homeDetailAdapter = HomeDetailAdapter(context!!, mongDDangProceedingItem, requestManager)
            homeDetailAdapter.setOnItemClickListener(this@HomeDetailFragment)
            v.home_detail_recyclerview.layoutManager = LinearLayoutManager(v.context)
            v.home_detail_recyclerview.adapter = homeDetailAdapter

        }

        v.home_detail_scheduled_btn.setOnClickListener {
            timeFlag = 1
            v.home_detail_scheduled_btn.isSelected = true
            v.home_detail_scheduled_btn.setTextColor(Color.parseColor("#ffffff"))
            v.home_detail_proceeding_btn.isSelected = false
            v.home_detail_proceeding_btn.setTextColor(Color.parseColor("#000000"))
            v.home_detail_completed_btn.isSelected = false
            v.home_detail_completed_btn.setTextColor(Color.parseColor("#000000"))
            //getHomeDetailList(v)
            while(checkFlag == 0){
                Log.v("asdf","리스트 받아오는중")

            }
            Log.v("asdf","리스트 다 받아옴")
            homeDetailAdapter = HomeDetailAdapter(context!!, mongDDangScheduledItem, requestManager)
            homeDetailAdapter.setOnItemClickListener(this@HomeDetailFragment)
            v.home_detail_recyclerview.layoutManager = LinearLayoutManager(v.context)
            v.home_detail_recyclerview.adapter = homeDetailAdapter


            //getMongDDangList(v)
        }

        v.home_detail_completed_btn.setOnClickListener {

            timeFlag = -1
            v.home_detail_completed_btn.isSelected = true
            v.home_detail_completed_btn.setTextColor(Color.parseColor("#ffffff"))
            v.home_detail_proceeding_btn.isSelected = false
            v.home_detail_proceeding_btn.setTextColor(Color.parseColor("#000000"))
            v.home_detail_scheduled_btn.isSelected = false
            v.home_detail_scheduled_btn.setTextColor(Color.parseColor("#000000"))
            //getHomeDetailList(v)
            while(checkFlag == 0){
                Log.v("asdf","리스트 받아오는중")

            }
            Log.v("asdf","리스트 다 받아옴")
            homeDetailAdapter = HomeDetailAdapter(context!!, mongDDangCompletedItem, requestManager)
            homeDetailAdapter.setOnItemClickListener(this@HomeDetailFragment)
            v.home_detail_recyclerview.layoutManager = LinearLayoutManager(v.context)
            v.home_detail_recyclerview.adapter = homeDetailAdapter

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

/*
        homeDetailAdapter = HomeDetailAdapter(context!!, homeDetailItem, requestManager)
        homeDetailAdapter.setOnItemClickListener(this@HomeDetailFragment)
        v.home_detail_recyclerview.layoutManager = LinearLayoutManager(v.context)
        v.home_detail_recyclerview.adapter = homeDetailAdapter
*/
    }



    private fun getMongDDangList(v : View) {

        var key : String = "66556959457770613830475572716d"

        pageNo = 1
        try {
            seoulNetworkService = SeoulApiController.getRetrofit().create(SeoulNetworkService::class.java)
            var getMonDDangResponse = seoulNetworkService.getMongDDangList(key, pageNo) // 네트워크 서비스의 getContent 함수를 받아옴
            Log.v("TAG","한강 몽땅 데이터 GET 통신 시작 전")
            getMonDDangResponse.enqueue(object : Callback<GetMongDDangResponse> {
                override fun onResponse(call: Call<GetMongDDangResponse>?, response: Response<GetMongDDangResponse>?) {
                    if(response!!.isSuccessful) {
                        Log.v("TAG","한강 몽땅 데이터 GET 통신 성공")
                        mongDDangRow = response!!.body()!!.Mgishangang

                        mongDDangData = mongDDangRow!!.row

                        if(mongDDangData.size == 0)
                        {
                            Log.v("TAG","한강 몽땅 데이터 값 없음.")
                        }
                        else
                        {
                            startDate(v)

                        }
                    }
                }

                override fun onFailure(call: Call<GetMongDDangResponse>?, t: Throwable?) {
                    Log.v("TAG","한강 몽땅 데이터 통신 실패" + t.toString())
                }
            })
        } catch (e: Exception) {
        }

    }

    fun imageSearch(v : View)
    {
        restNetworkService = RestApplicationController.getRetrofit().create(RestNetworkService::class.java)
        if(timeFlag == -1) {

            Log.v("asdf", "이미 완료됨")
            for (i in 0..mongDDangCompletedItem.size-1) {
                Log.v("TAG", "이미지 검색 통신 시작 전 = " + i)
                var getImageSearchResponse = restNetworkService.getImageSearch("KakaoAK 298be2e3612229355b9d2e28bb10912e", mongDDangCompletedItem[i].homeDetailActivityName!!)
                getImageSearchResponse.enqueue(object : Callback<GetImageSearchResponse> {
                    override fun onResponse(call: Call<GetImageSearchResponse>?, response: Response<GetImageSearchResponse>?) {
                        Log.v("TAG", "이미지 검색 통신 시작")
                        if (response!!.isSuccessful) {

                            Log.v("TAG", "이미지 검색 통신 성공")

                            if (response!!.body()!!.documents!!.size == 0) {
                                mongDDangCompletedItem[i].homeDetailActivityImgUrl = "https://2.bp.blogspot.com/-a7VX4tT-g4k/WdEZ8eaDrWI/AAAAAAAC-mA/O5cw44tb1TMA7gJzhvnX8b1ObO0y80-tACLcBGAs/s1600/%25ED%2595%259C%25EA%25B0%2595%2B%25EA%25B8%25B8%25EC%259D%25B4.jpg"
                            } else {
                                mongDDangCompletedItem[i].homeDetailActivityImgUrl = response!!.body()!!.documents[0].image_url!!
                            }

                            if (i == 9) {
                                checkFlag = 1
                            }


                        } else {
                            Log.v("TAG", "이미지 검색 값 가져오기 실패")
                        }
                    }

                    override fun onFailure(call: Call<GetImageSearchResponse>?, t: Throwable?) {
                        Log.v("TAG", "이미지 서버 통신 실패" + t.toString())
                    }

                })
            }

        }
        else if(timeFlag == 0){
            Log.v("asdf", "이미 진행중")

            for(i in 0..mongDDangProceedingItem.size-1) {
                Log.v("TAG","이미지 검색 통신 시작 전")
                var getImageSearchResponse = restNetworkService.getImageSearch("KakaoAK 298be2e3612229355b9d2e28bb10912e", mongDDangProceedingItem[i].homeDetailActivityName!!)
                getImageSearchResponse.enqueue(object : Callback<GetImageSearchResponse> {
                    override fun onResponse(call: Call<GetImageSearchResponse>?, response: Response<GetImageSearchResponse>?) {
                        Log.v("TAG","이미지 검색 통신 시작")
                        if(response!!.isSuccessful)
                        {
                            Log.v("TAG","이미지 검색 통신 성공")

                            if(response!!.body()!!.documents!!.size == 0) {
                                mongDDangProceedingItem[i].homeDetailActivityImgUrl = "https://2.bp.blogspot.com/-a7VX4tT-g4k/WdEZ8eaDrWI/AAAAAAAC-mA/O5cw44tb1TMA7gJzhvnX8b1ObO0y80-tACLcBGAs/s1600/%25ED%2595%259C%25EA%25B0%2595%2B%25EA%25B8%25B8%25EC%259D%25B4.jpg"
                            }
                            else{
                                mongDDangProceedingItem[i].homeDetailActivityImgUrl = response!!.body()!!.documents[0].image_url!!
                            }

                        }
                        else
                        {
                            Log.v("TAG","이미지 검색 값 가져오기 실패")
                        }
                    }
                    override fun onFailure(call: Call<GetImageSearchResponse>?, t: Throwable?) {
                        Log.v("TAG","이미지 서버 통신 실패"+t.toString())
                    }
                })
            }

        }
        else if(timeFlag == 1){
            Log.v("asdf", "이미 예정중")

            for(i in 0..mongDDangScheduledItem.size-1) {
                Log.v("TAG","이미지 검색 통신 시작 전")
                var getImageSearchResponse = restNetworkService.getImageSearch("KakaoAK 298be2e3612229355b9d2e28bb10912e", mongDDangScheduledItem[i].homeDetailActivityName!!)
                getImageSearchResponse.enqueue(object : Callback<GetImageSearchResponse> {
                    override fun onResponse(call: Call<GetImageSearchResponse>?, response: Response<GetImageSearchResponse>?) {
                        Log.v("TAG","이미지 검색 통신 시작")
                        if(response!!.isSuccessful)
                        {
                            Log.v("TAG","이미지 검색 통신 성공")

                            if(response!!.body()!!.documents!!.size == 0) {
                                mongDDangScheduledItem[i].homeDetailActivityImgUrl = "https://2.bp.blogspot.com/-a7VX4tT-g4k/WdEZ8eaDrWI/AAAAAAAC-mA/O5cw44tb1TMA7gJzhvnX8b1ObO0y80-tACLcBGAs/s1600/%25ED%2595%259C%25EA%25B0%2595%2B%25EA%25B8%25B8%25EC%259D%25B4.jpg"
                            }
                            else{
                                mongDDangScheduledItem[i].homeDetailActivityImgUrl = response!!.body()!!.documents[0].image_url!!
                            }

                        }
                        else
                        {
                            Log.v("TAG","이미지 검색 값 가져오기 실패")
                        }
                    }
                    override fun onFailure(call: Call<GetImageSearchResponse>?, t: Throwable?) {
                        Log.v("TAG","이미지 서버 통신 실패"+t.toString())
                    }
                })
            }


        }


    }

    fun Check(currentDate : Date, selectedStartDate : Date, selectedFinishDate : Date) : Int{
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

        if(currentDate.compareTo(selectedFinishDate) > 0){
            val dateA = simpleDateFormat.format(currentDate)
            val dateB = simpleDateFormat.format(selectedFinishDate)
            if(dateA.equals(dateB)){
                Log.v("ASdf", "둘의 날짜가 같습니다.")
                return 0
            }
            else{
                Log.v("ASdf", "활동 끝 날짜 < 현재 날짜")
                return -1;
            }

        }else if(currentDate.compareTo(selectedStartDate) < 0){
            Log.v("ASdf", "현재 날짜 < 활동 시작 날짜.")
            return 1     }
        else{
            return 0
            Log.v("ASdf", "진행 중인 활동")
        }
/*
        if(a.compareTo(b) > 0){
            Log.v("adsf" , "a가 b보다 날짜가 느립니다.")
            return "a가 b보다 날짜가 느립니다.";
        }else if(a.compareTo(b) < 0){
            Log.v("adsf" , "a가 b보다 날짜가 빠릅니다.")
            return "a가 b보다 날짜가 빠릅니다.";
        }else{
            return "날짜가 같습니다.";
        }
        */
    }

    fun startDate(v : View ) {
        Log.v("asdf", "몽땅 데이터 크기 = " + mongDDangData.size)
        for (i in 0..mongDDangData.size - 1) {

            Log.v("er", "한강 몽땅 데이터[" + i + "]번째 값 = " + mongDDangData[i].COT_VALUE_02)




            //selectedDay = mongDDangData[i].COT_VALUE_02!!.replace("(월)", "").replace("(화)", "").replace("(수)","").replace("(목)","").replace("(금)","").replace("(토)","").replace("(일)","").replace("월",".").replace("일","").replace(" ","")
            selectedDay = mongDDangData[i].COT_VALUE_02!!.replace("[^0-9,.,~,/]".toRegex(), "")
            selectedDay = selectedDay.replace("9~","9")
            selectedDay = selectedDay.replace("194", "19")
            selectedDay = selectedDay.replace("9,", "9")
            Log.v("asdf", "선택 변환 값 = " + selectedDay)
            if(selectedDay == "7.28~8.198.1~8.121"){
                startDate = "2018-07-28"
                finishDate = "2018-08-12"
                Log.v("asdf", "최종 시작 일 = " + startDate)
                Log.v("asdf", "최종 마지막 일 = " + finishDate)
            }
            else if(selectedDay == "7.20~8.198.1411"){
                startDate = "2018-07-20"
                finishDate = "2018-08-19"
                Log.v("asdf", "최종 시작 일 = " + startDate)
                Log.v("asdf", "최종 마지막 일 = " + finishDate)
            }
            else{
                if(selectedDay == "")
                {
                    Log.v("asdf", "프로그램별 상이")
                }
                else{
                    if(selectedDay.indexOf(".") == -1)
                    {
                        Log.v("adsf","8월4일")
                        if(Integer.parseInt(selectedDay) > 50){
                            selectedDay = selectedDay.substring(0,1) + "." + selectedDay.substring(1,2)
                        }
                    }

                    startMonth = selectedDay.substring(0,selectedDay.indexOf("."))
                    if((Integer.parseInt(startMonth)) < 10){
                        startMonth = "0" + startMonth
                    }
                    //Log.v("asdf", "선택 시작 월 = " + startMonth + "월")

                    if(selectedDay.indexOf("~") != -1){
                        selectedDay = selectedDay.replace(".,", "")
                        startDay = selectedDay.substring(selectedDay.indexOf(".")+1, selectedDay.indexOf("~"))
                        if((Integer.parseInt(startDay)) < 10){
                            startDay = "0" + startDay }
                        if(selectedDay.indexOf(".",selectedDay.indexOf("~")) == -1){
                            finishMonth = startMonth
                        }
                        else{
                            finishMonth = selectedDay.substring(selectedDay.indexOf("~")+1, selectedDay.indexOf(".",selectedDay.indexOf("~")))
                        }

                        if(finishMonth.indexOf(",") != -1){
                            Log.v("Asdf", "테스트 = " + finishMonth)
                            finishMonth = finishMonth.substring(finishMonth.indexOf(",") + 1, finishMonth.length)
                            finishDay = selectedDay.substring(selectedDay.indexOf("~", selectedDay.length-3)+1,selectedDay.length)
                            if((Integer.parseInt(finishMonth)) < 10){

                                finishMonth = "0" + finishMonth }
                            if((Integer.parseInt(finishDay)) < 10){
                                finishDay = "0" + finishDay }
                        }
                        else{
                            if(selectedDay.indexOf(".", selectedDay.length-3) == -1)
                            {


                                finishDay = selectedDay.substring(selectedDay.indexOf("~")+1,selectedDay.length)

                                Log.v("ASDf","요기이1")
                            }
                            else{
                                finishDay = selectedDay.substring(selectedDay.indexOf(".", selectedDay.length-3)+1,selectedDay.length)
                                Log.v("ASDf","요기이2")
                            }
                            if((Integer.parseInt(finishMonth)) < 10){

                                finishMonth = "0" + finishMonth
                            }
                            if(finishDay.indexOf(",") != -1){
                                finishDay = finishDay.substring(0, finishDay.indexOf(","))
                            }
                            if((Integer.parseInt(finishDay)) < 10){
                                finishDay = "0" + finishDay }
                        }
                    }
                    else if(selectedDay.indexOf("/") != -1){
                        startDay = selectedDay.substring(selectedDay.indexOf(".")+1, selectedDay.indexOf("/"))
                        finishMonth = selectedDay.substring(selectedDay.indexOf("/",selectedDay.length-5)+1, selectedDay.indexOf(".",selectedDay.length-5))
                        finishDay = selectedDay.substring(selectedDay.indexOf(".", selectedDay.length-3)+1,selectedDay.length)
                        if((Integer.parseInt(finishMonth)) < 10){

                            finishMonth = "0" + finishMonth }
                        if((Integer.parseInt(finishDay)) < 10){
                            finishDay = "0" + finishDay }
                    }
                    else if(selectedDay.indexOf(",") != -1){
                        startDay = selectedDay.substring(selectedDay.indexOf(".")+1, selectedDay.indexOf(","))
                        if(selectedDay.indexOf(",", selectedDay.length-2) != 1){
                            finishMonth = startMonth
                            finishDay = selectedDay.substring(selectedDay.indexOf(",",selectedDay.length-3)+1, selectedDay.length)
                            if(selectedDay.indexOf(".",selectedDay.length-3) != -1){
                                finishMonth = selectedDay.substring(selectedDay.indexOf(",",selectedDay.length-6)+1, selectedDay.indexOf(".", selectedDay.length-3))
                                finishDay = selectedDay.substring(selectedDay.indexOf(".", selectedDay.length-3)+1, selectedDay.length)
                            }
                        }
                        else{
                            if(selectedDay.indexOf(".",selectedDay.length-2) != -1){
                                finishMonth = selectedDay.substring(selectedDay.indexOf(",",selectedDay.length-5)+1, selectedDay.indexOf(".", selectedDay.length-2))
                                finishDay = selectedDay.substring(selectedDay.indexOf(".", selectedDay.length-2)+1, selectedDay.length)
                            }
                            else{
                                finishMonth = selectedDay.substring(selectedDay.indexOf(",", selectedDay.length-6)+1, selectedDay.indexOf(".", selectedDay.length-4))
                                finishDay = selectedDay.substring(selectedDay.indexOf(".", selectedDay.length-3)+1,selectedDay.length)

                            }

                        }
                        if((Integer.parseInt(finishMonth)) < 10){

                            finishMonth = "0" + finishMonth }
                        if((Integer.parseInt(finishDay)) < 10){
                            finishDay = "0" + finishDay }
                    }
                    else{
                        startDay = selectedDay.substring(selectedDay.indexOf(".")+1, selectedDay.length)
                        startDay = startDay.replace("[^0-9]".toRegex(), "")
                        finishMonth = startMonth
                        finishDay = startDay
                        if((Integer.parseInt(startDay)) < 10){
                            startDay = "0" + startDay
                        }
                        //
                    }

                    //Log.v("asdf", "선택 마지막 월 = " + finishMonth + "월")
                    //Log.v("asdf", "선택 마지막 일 = " + finishDay + "일")
                    //Log.v("asdf", "선택 시작 일 = " + startDay + "일")
                    startDate = "2018-" + startMonth + "-" + startDay
                    finishDate = "2018-" + finishMonth + "-" + finishDay
                    Log.v("asdf", "최종 시작 일 = " + startDate.replace("00", "0"))
                    Log.v("asdf", "최종 마지막 일 = " + finishDate.replace("00", "0"))

                }


            }


            //startMonth = selectedDay.substring(selectedDay.indexOf("."))


            if(dateFlag ==1 ){
                var translateStartDate = SimpleDateFormat("yyyy-MM-dd").parse(startDate)
                var translateFinishDate = SimpleDateFormat("yyyy-MM-dd").parse(finishDate)

                Log.v("asdf","몽땅 최종 시작 날짜 = " + translateStartDate.toString())

                val df= SimpleDateFormat("yyyy-MM-dd");
                val currentDate = Date()

                //-1이면 완료된 행사, 0이면 진행 중인 행사, 1이면 예정인 행사
                timeFlag = Check(currentDate, translateStartDate, translateFinishDate)
                if(timeFlag == -1){
                    Log.v("asdf", "완료된 행사")
                    mongDDangCompletedItem.add(MongDDangItem(mongDDangData[i].COT_CONTS_NAME,mongDDangData[i].COT_VALUE_02,"null"))
                }
                else if(timeFlag == 1){
                    Log.v("asdf", "예정인 행사")
                    mongDDangScheduledItem.add(MongDDangItem(mongDDangData[i].COT_CONTS_NAME,mongDDangData[i].COT_VALUE_02,"null"))
                }
                else{
                    Log.v("asdf", "진행 중인 행사")
                    mongDDangProceedingItem.add(MongDDangItem(mongDDangData[i].COT_CONTS_NAME,mongDDangData[i].COT_VALUE_02,"null"))
                }

            }

            else{

            }
        }
        if(timeFlag == -1){
            imageSearch(v)
        }
        else if(timeFlag == 0){
            imageSearch(v)
        }
        else if(timeFlag == 1){
            imageSearch(v)
        }


    }
}
