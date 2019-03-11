package dsm.android.v3.ui.musicApplyLog

import dsm.android.v3.ui.musicApply.MusicApplyModel
import  java.util.ArrayList

interface MusicApplyLogNavigator {
    fun setApplyList(models: ArrayList<MusicApplyModel.MusicApplyDataModel>)
    fun backApplyGoing()
    fun toast(message : String)

    interface MusicApplyLogRv{
        fun logItemClickTrue(model: MusicApplyModel.MusicApplyDataModel)
        fun logItemClickFalse(model: MusicApplyModel.MusicApplyDataModel)
    }
}