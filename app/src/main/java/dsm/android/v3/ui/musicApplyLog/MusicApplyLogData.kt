package dsm.android.v3.ui.musicApplyLog

import dsm.android.v3.model.MusicApplyLogItemModel

object MusicApplyLogData{
    val mondayItemList: ArrayList<MusicApplyLogItemModel> = arrayListOf(
        MusicApplyLogItemModel("벌써 12시", "청하", "이주용"),
        MusicApplyLogItemModel("첫눈에", "헤이즈", "김영찬"))

    val tuesdayItemList: ArrayList<MusicApplyLogItemModel> = arrayListOf(
        MusicApplyLogItemModel("멍청이", "화사", "송진우"),
        MusicApplyLogItemModel("달라달라", "있지","이종현")
    )

    val wednesdayItemList: ArrayList<MusicApplyLogItemModel> = arrayListOf(
        MusicApplyLogItemModel("이 노래가 클럽에서 나온다면", "우디", "이성현"),
        MusicApplyLogItemModel("그때가 좋았어", "케이시","ㅁㄴㅇㄹ")
    )

    val thursdayItemList: ArrayList<MusicApplyLogItemModel> = arrayListOf(
        MusicApplyLogItemModel("넘쳐흘러", "엠씨더맥스","ㅁㄴㅇㄹ"),
        MusicApplyLogItemModel("옥탑방", "엔플라잉","ㅁㄴㅇㄹ")
    )

    val fridayItemList: ArrayList<MusicApplyLogItemModel> = arrayListOf(
        MusicApplyLogItemModel("오랜만이야", "로꼬", "ㅁㄴㅇㄹ"),
        MusicApplyLogItemModel("OPPA", "로꼬","sdfsdf")
    )

    val deleteData = ArrayList<MusicApplyLogItemModel>()

    val nothingData : ArrayList<MusicApplyLogItemModel> = arrayListOf(
        MusicApplyLogItemModel("신청곡이 없습니다.", "눌러서 노래를 신청해주세요.", "신청없음")
    )
}