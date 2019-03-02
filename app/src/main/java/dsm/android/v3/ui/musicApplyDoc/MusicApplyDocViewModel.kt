package dsm.android.v3.ui.musicApplyDoc

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

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
            contract.backApplyMusic()
        }
    }

    fun musicApplyDocClickBack() {
        contract.backApplyMusic()
    }

}