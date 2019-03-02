package dsm.android.v3.ui.musicApply

import com.google.gson.annotations.SerializedName
import  java.util.*

data class MusicApplyDataModel(
    @SerializedName("mon")
    var mondayList: ArrayList<MusicApplyDataModel>,

    @SerializedName("tue")
    var tuesdayList: ArrayList<MusicApplyDataModel>,

    @SerializedName("wed")
    var wednesdayList: ArrayList<MusicApplyDataModel>,

    @SerializedName("thu")
    var thursdayList: ArrayList<MusicApplyDataModel>,

    @SerializedName("fri")
    var fridayList: ArrayList<MusicApplyDataModel>
) {
    data class MusicApplyDataModel(
        @SerializedName("applyDate")
        var applyDate: String,

        @SerializedName("id")
        var id: Int,

        @SerializedName("musicName")
        var musicName: String,

        @SerializedName("singer")
        var singer: String,

        @SerializedName("studentId")
        var studentId: String,

        @SerializedName("studentName")
        var studentName: String
    )
}