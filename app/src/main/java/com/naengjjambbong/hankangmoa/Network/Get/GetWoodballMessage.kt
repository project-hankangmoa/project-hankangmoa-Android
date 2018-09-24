package com.naengjjambbong.hankangmoa.Network.Get

import com.naengjjambbong.hankangmoa.Network.Get.RowData.GetCampingRowData
import com.naengjjambbong.hankangmoa.Network.Get.RowData.GetWoodballRowData

data class GetWoodballMessage (

        var row : ArrayList<GetWoodballRowData>

)