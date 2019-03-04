package dsm.android.v3.ui.musicApplyLog

import dsm.android.v3.ui.musicApply.MusicApplyDataModel
import  java.util.ArrayList

interface MusicApplyLogNavigator {
    fun setApplyList(models: ArrayList<MusicApplyDataModel.MusicApplyDataModelSub>)
    fun backApplyGoing()
    fun toast(message : String)

    interface MusicApplyLogRv{
        fun logItemClickTrue(model:  MusicApplyDataModel.MusicApplyDataModelSub)
        fun logItemClickFalse(model:  MusicApplyDataModel.MusicApplyDataModelSub)
    }
}