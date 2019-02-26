package dsm.android.v3.ui.musicApplyLog

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class MusicApplyLogViewModel(val contract: MusicApplyLogNavigator, val title: String) : ViewModel() {

    val logTitle = MutableLiveData<String>()

    init {
        logTitle.value = title

        when (logTitle.value) {
            "월요일 기상음악" -> contract.setApplyList(MusicApplyLogData.mondayItemList)
            "화요일 기상음악" -> contract.setApplyList(MusicApplyLogData.tuesdayItemList)
            "수요일 기상음악" -> contract.setApplyList(MusicApplyLogData.wednesdayItemList)
            "목요일 기상음악" -> contract.setApplyList(MusicApplyLogData.thursdayItemList)
            "금요일 기상음악" -> contract.setApplyList(MusicApplyLogData.fridayItemList)
        }
    }

    fun musicApply() {
        //서버에서 받아온게 비어있을 때 눌렀을때 신청이미지로 intent
    }

    fun musicApplyStatus() {

    }


fun musicApplyClickDelete() {
    // 서버 통신 필요
    //아예 삭제해버리면 안돼고 아이템은 2개로 유지
    when (logTitle.value) {
        "월요일 기상음악" -> {
            MusicApplyLogData.mondayItemList.removeAll(MusicApplyLogData.deleteData)
            contract.setApplyList(MusicApplyLogData.nothingData)
        }
        "화요일 기상음악" -> {
            MusicApplyLogData.tuesdayItemList.removeAll(MusicApplyLogData.deleteData)
            contract.setApplyList(MusicApplyLogData.nothingData)
        }
        "수요일 기상음악" -> {
            MusicApplyLogData.wednesdayItemList.removeAll(MusicApplyLogData.deleteData)
            contract.setApplyList(MusicApplyLogData.nothingData)
        }
        "목요일 기상음악" -> {
            contract.setApplyList(MusicApplyLogData.nothingData)
        }
        "금요일 기상음악" -> {
            MusicApplyLogData.fridayItemList.removeAll(MusicApplyLogData.deleteData)
            contract.setApplyList(MusicApplyLogData.nothingData)
        }
    }
    contract.backApplyGoing()
}

fun musicApplyLogClickBack() {
    contract.backApplyGoing()
}
}