package dsm.android.v3.ui.musicApplyLog

import dsm.android.v3.ui.musicApply.MusicApplyDataModel
import  java.util.ArrayList

interface MusicApplyLogNavigator {
    fun setApplyList(models: ArrayList<MusicApplyDataModel.MusicApplyDataModel>)
    fun backApplyGoing()
    fun toast(message : String)

    interface MusicApplyLogRv{
        fun logItemClickTrue(model:  MusicApplyDataModel.MusicApplyDataModel)
        fun logItemClickFalse(model:  MusicApplyDataModel.MusicApplyDataModel)
    }
}