package dsm.android.v3.ui.applyGoingLog

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.view.View
import dsm.android.v3.connecter.api
import dsm.android.v3.util.getToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplyGoingLogViewModel(val contract: ApplyGoingLogContract, title: String): ViewModel() {

    val logTitle = MutableLiveData<String>()

    init {
        logTitle.value = title

        when(logTitle.value){
            "토요외출" -> contract.setApplyList(ApplyGoingLogData.saturdayItemList)
            "일요외출" -> contract.setApplyList(ApplyGoingLogData.sundayItemList)
            "평일외출" -> contract.setApplyList(ApplyGoingLogData.workdayItemList)
        }
    }

    fun applyGoingLogClickBack() {
        contract.backApplyGoing()
    }
}