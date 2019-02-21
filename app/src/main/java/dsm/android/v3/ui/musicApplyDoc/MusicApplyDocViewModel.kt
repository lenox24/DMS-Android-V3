package dsm.android.v3.ui.musicApplyDoc

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import dsm.android.v3.model.MusicApplyLogItemModel
import dsm.android.v3.ui.musicApplyLog.MusicApplyLogData

class MusicApplyDocViewModel(val contract: MusicApplyDocNavigator, val currentItem: Int) : ViewModel() {

    val applyMusicName = MutableLiveData<String>()
    val applyMusicArtist = MutableLiveData<String>()
    val applyMusicStudent = MutableLiveData<String>() //서버에서 받아온 학생이름

    fun musicApplyDocClickApply() {
        if (applyMusicName.value.isNullOrBlank()) {
            contract.setErrorApplyMusicName()
        } else if (applyMusicArtist.value.isNullOrBlank()) {
            contract.setErrorApplyArtist()
        } else {
            // 서버 통신 필요
            when (currentItem) {
                0 -> {
                    if (MusicApplyLogData.mondayItemList.size != 2) {
                        MusicApplyLogData.mondayItemList.add(
                            MusicApplyLogItemModel("${applyMusicName.value}", "${applyMusicArtist.value}", "${applyMusicStudent.value}")
                        )
                    } else {
                        contract.createListFullWarningToast()
                    }
                }
                1 -> {
                    if (MusicApplyLogData.tuesdayItemList.size != 2) {
                        MusicApplyLogData.tuesdayItemList.add(
                            MusicApplyLogItemModel("${applyMusicName.value}", "${applyMusicArtist.value}", "${applyMusicStudent.value}")
                        )
                    } else {
                        contract.createListFullWarningToast()
                    }
                }
                2 -> {
                    if (MusicApplyLogData.wednesdayItemList.size != 2) {
                        MusicApplyLogData.wednesdayItemList.add(
                            MusicApplyLogItemModel("${applyMusicName.value}", "${applyMusicArtist.value}", "${applyMusicStudent.value}")
                        )
                    } else {
                        contract.createListFullWarningToast()
                    }
                }
                3 -> {
                    if (MusicApplyLogData.thursdayItemList.size != 2) {
                        MusicApplyLogData.thursdayItemList.add(
                                MusicApplyLogItemModel("${applyMusicName.value}", "${applyMusicArtist.value}", "${applyMusicStudent.value}"))
                    } else {
                        contract.createListFullWarningToast()
                    }
                }
                4 -> {
                    if (MusicApplyLogData.fridayItemList.size != 2) {
                        MusicApplyLogData.fridayItemList.add(
                            MusicApplyLogItemModel("${applyMusicName.value}", "${applyMusicArtist.value}", "${applyMusicStudent.value}"))
                    } else {
                        contract.createListFullWarningToast()
                    }
                }
            }
            contract.backApplyMusic()
        }
    }

    fun musicApplyDocClickBack() {
        contract.backApplyMusic()
    }

}