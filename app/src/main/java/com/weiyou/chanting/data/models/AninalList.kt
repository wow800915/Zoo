package com.weiyou.chanting.data.models

import com.google.gson.annotations.SerializedName

class AninalList {
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
        val results: List<Exhibit>? = null
    }

    class Exhibit {
        @SerializedName("_id")
        val id = 0

        @SerializedName("E_no")
        val exhibitNo: String? = null

        @SerializedName("E_Category")
        val category: String? = null

        @SerializedName("E_Name")
        val name: String? = null

        @SerializedName("E_Pic_URL")
        val picUrl: String? = null

        @SerializedName("E_Info")
        val info: String? = null

        @SerializedName("E_Memo")
        val memo: String? = null

        @SerializedName("E_Geo")
        val geo: String? = null

        @SerializedName("E_URL")
        val url: String? = null
    }
}
