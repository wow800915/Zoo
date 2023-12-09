package com.weiyou.zoo.data.models

import com.google.gson.annotations.SerializedName

class AreaList {
    @SerializedName("result")
    val result: Result? = null

    class Result {
        @SerializedName("limit")
        val limit = 0

        @SerializedName("offset")
        val offset = 0

        @SerializedName("count")
        val count = 0

        @SerializedName("sort")
        val sort: String? = null

        @SerializedName("results")
        val results: List<Area>? = null
    }

    class Area {
        @SerializedName("_id")
        val _id = 0

        @SerializedName("e_no")
        val e_no: String? = null

        @SerializedName("e_category")
        val e_category: String? = null

        @SerializedName("e_name")
        val e_name: String? = null

        @SerializedName("e_pic_url")
        val e_pic_url: String? = null

        @SerializedName("e_info")
        val e_info: String? = null

        @SerializedName("e_memo")
        val e_memo: String? = null

        @SerializedName("e_geo")
        val e_geo: String? = null

        @SerializedName("e_url")
        val e_url: String? = null
    }
}
