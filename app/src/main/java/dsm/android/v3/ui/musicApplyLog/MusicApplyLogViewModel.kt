package dsm.android.v3.ui.musicApplyLog

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.view.View
import dsm.android.v3.connecter.Connecter.api
import dsm.android.v3.ui.musicApplyLog.MusicApplyLogData.deleteDataList
import dsm.android.v3.util.getToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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


    fun musicApplyClickDelete(view: View) {
        for (deleteData in deleteDataList) {
            Log.e("dsgsdgdsg", "${deleteData.id}")
            api.deleteMusic(getToken(view.context), hashMapOf("applyId" to deleteData.id))
                .enqueue(object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        contract.toast(
                            when (response.code()) {
                                200 -> "기상음악 취소 성공"
                                204 -> "존재하지 않는 applyId"
                                403 -> "권한이 없습니다."
                                else -> "오류코드: ${response.code()}"
                            }
                        )
                        contract.backApplyGoing()
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        contract.toast("오류가 발생했습니다.")
                    }
                })
        }
    }

    fun musicApplyLogClickBack() {
        contract.backApplyGoing()
    }
}