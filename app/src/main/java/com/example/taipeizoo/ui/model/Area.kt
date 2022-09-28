package com.example.taipeizoo.ui.model

/*
data class Importdate(
    val data: String,
    val timezone_type: Int,
    val timezone: String
)*/

data class AreaInfo(
    val _id: Int,
    val e_name: String,
    val e_no: Int,
    val e_category: String,

    val e_pic_url: String,
    val e_info: String,
    val e_memo: String,
    val e_geo: String,
    val e_url: String
    //val _importdate : ArrayList<Importdate>

//    val plantInfo: valPlantInfo,
//    var pic_bitmap: Bitmap
)

data class AreaResult(
    val limit: Int,
    //val offset: Int,
    //val count: Int,
    //val sort: String,
    val results: ArrayList<AreaInfo>
)

data class AreaResponse(
    val result: AreaResult
)
