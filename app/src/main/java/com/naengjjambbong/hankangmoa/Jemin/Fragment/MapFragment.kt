package com.naengjjambbong.hankangmoa.Jemin.Fragment

import android.view.ViewGroup
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import com.naengjjambbong.hankangmoa.R
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class MapFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_map, container, false)//지도
        val mapView = MapView(activity)
        val mapViewContainer = v.findViewById(R.id.map_view) as ViewGroup
        mapViewContainer.addView(mapView)

        // 중심점 변경 - 예제 좌표는 서울 남산
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.54892296550104, 126.99089033876304), true)

        // 줌 레벨 변경
        mapView.setZoomLevel(4, true)

        //마커 찍기
        val MARKER_POINT = MapPoint.mapPointWithGeoCoord(37.54892296550104, 126.99089033876304)
        val marker = MapPOIItem()
        marker.itemName = "Default Marker"
        marker.tag = 0
        marker.mapPoint = MARKER_POINT
        marker.markerType = MapPOIItem.MarkerType.BluePin // 기본으로 제공하는 BluePin 마커 모양.
        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mapView.addPOIItem(marker)

        return v
    }
}