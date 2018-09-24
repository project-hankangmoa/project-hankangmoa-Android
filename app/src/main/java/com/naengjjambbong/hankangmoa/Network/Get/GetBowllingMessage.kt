package com.naengjjambbong.hankangmoa.Network.Get

import com.naengjjambbong.hankangmoa.Network.Get.RowData.GetBowllingRowData
import com.naengjjambbong.hankangmoa.Network.Get.RowData.GetCampingRowData

data class GetBowllingMessage (

        var row : ArrayList<GetBowllingRowData>

)