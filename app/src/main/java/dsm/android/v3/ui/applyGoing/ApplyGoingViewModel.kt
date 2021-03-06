package dsm.android.v3.ui.applyGoing

import android.arch.lifecycle.*
import android.content.Context
import dsm.android.v3.connecter.api
import dsm.android.v3.model.ApplyGoingModel
import dsm.android.v3.ui.applyGoingLog.ApplyGoingLogData.saturdayItemList
import dsm.android.v3.ui.applyGoingLog.ApplyGoingLogData.sundayItemList
import dsm.android.v3.ui.applyGoingLog.ApplyGoingLogData.workdayItemList
import dsm.android.v3.util.LifecycleCallback
import dsm.android.v3.util.getToken
import dsm.android.v3.util.saveToken
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplyGoingViewModel(val contract: ApplyGoingContract): ViewModel(), LifecycleCallback{

    override fun apply(event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_START -> {
                api.getGoingOutInfo(getToken(contract as Context)).enqueue(object: Callback<ApplyGoingModel>{
                    override fun onResponse(call: Call<ApplyGoingModel>, response: Response<ApplyGoingModel>) {
                        when(response.code()){
                            200 -> setApplyGoingData(response.body()!!)
                            204 -> {
                                val applyGoingList = ApplyGoingModel(ArrayList(), ArrayList(), ArrayList())
                                setApplyGoingData(applyGoingList)
                                contract.createShortToast("외출신청 정보가 없습니다.")
                            }
                            403 -> contract.createShortToast("외출신청 정보 조회 권한이 없습니다.")
                            500 -> contract.createShortToast("로그인이 필요합니다.")
                            else -> contract.createShortToast("오류코드: ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<ApplyGoingModel>, t: Throwable) {
                        contract.createShortToast("오류가 발생했습니다.")
                    }
                })
                contract.setViewPager(saturdayItemList.size, sundayItemList.size ,workdayItemList.size)
            }
        }
    }

    fun setApplyGoingData(applyGoingList: ApplyGoingModel) {
        saturdayItemList = applyGoingList.saturdayList
        sundayItemList = applyGoingList.sundayList
        workdayItemList = applyGoingList.workdayList
        contract.setViewPager(saturdayItemList.size, sundayItemList.size, workdayItemList.size)
    }

    fun applyGoingClickDoc() = contract.intentApplyGoingDoc()
}
