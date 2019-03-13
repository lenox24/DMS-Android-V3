package dsm.android.v3.ui.musicApply

import android.arch.lifecycle.*
import android.content.Context
import dsm.android.v3.connecter.Connecter.api
import dsm.android.v3.ui.musicApplyLog.MusicApplyLogData.deleteDataList
import dsm.android.v3.ui.musicApplyLog.MusicApplyLogData.fridayItemList
import dsm.android.v3.ui.musicApplyLog.MusicApplyLogData.mondayItemList
import dsm.android.v3.ui.musicApplyLog.MusicApplyLogData.thursdayItemList
import dsm.android.v3.ui.musicApplyLog.MusicApplyLogData.tuesdayItemList
import dsm.android.v3.ui.musicApplyLog.MusicApplyLogData.wednesdayItemList
import dsm.android.v3.util.getToken
import dsm.android.v3.util.saveToken
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MusicApplyViewModel(val contract: MusicApplyNavigator, val event: Lifecycle.Event) : ViewModel() {

    init {
        when(event) {
            Lifecycle.Event.ON_RESUME -> {
//                saveToken(contract as Context, "")
             api.getMusic(getToken(contract as Context)).enqueue(object: Callback<MusicApplyModel> {
                    override fun onResponse(call: Call<MusicApplyModel>, response: Response<MusicApplyModel>) {
                        when(response.code()){
                            200 -> setMusicApplyData(response.body()!!)
                            204 -> contract.toast("기상음악신청 정보가 없습니다.")
                            403 -> contract.toast("기상음악신청 정보 조회 권한이 없습니다.")
                            else -> contract.toast("오류코드: ${response.code()}")
                        }
                    }
                    override fun onFailure(call: Call<MusicApplyModel>, t: Throwable) {
                    }
                })
                contract.setViewPager(mondayItemList.size, tuesdayItemList.size , wednesdayItemList.size, thursdayItemList.size, fridayItemList.size)
                deleteDataList.clear()
            }
        }
    }

    fun setMusicApplyData(musicApplyList: MusicApplyModel) {
        mondayItemList = musicApplyList.mondayList
        tuesdayItemList = musicApplyList.tuesdayList
        wednesdayItemList = musicApplyList.wednesdayList
        thursdayItemList = musicApplyList.thursdayList
        fridayItemList = musicApplyList.fridayList
        contract.setViewPager(mondayItemList.size, tuesdayItemList.size , wednesdayItemList.size, thursdayItemList.size, fridayItemList.size)
    }

    fun musicApplyClickLog() = contract.intentMusicApplyLog()

}