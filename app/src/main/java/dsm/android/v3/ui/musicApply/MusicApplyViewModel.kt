package dsm.android.v3.ui.musicApply

import android.arch.lifecycle.*
import dsm.android.v3.ui.musicApplyLog.MusicApplyLogData.deleteData
import dsm.android.v3.ui.musicApplyLog.MusicApplyLogData.fridayItemList
import dsm.android.v3.ui.musicApplyLog.MusicApplyLogData.mondayItemList
import dsm.android.v3.ui.musicApplyLog.MusicApplyLogData.thursdayItemList
import dsm.android.v3.ui.musicApplyLog.MusicApplyLogData.tuesdayItemList
import dsm.android.v3.ui.musicApplyLog.MusicApplyLogData.wednesdayItemList

class MusicApplyViewModel(val contract: MusicApplyNavigator, val event: Lifecycle.Event) : ViewModel() {

    init {
        when(event) {
            Lifecycle.Event.ON_START -> {
                deleteData.clear()
                settingViewPager()
            }
        }
    }

    fun settingViewPager() = contract.setViewPager(
        mondayItemList.size,
        tuesdayItemList.size,
        wednesdayItemList.size,
        thursdayItemList.size,
        fridayItemList.size
    )

    fun MusicApplyClickLog() = contract.intentMusicApplyLog()

}