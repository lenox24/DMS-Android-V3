package dsm.android.v3.ui.musicApplyLog

import dsm.android.v3.model.MusicApplyLogItemModel

interface MusicApplyLogNavigator {
    fun setApplyList(models: ArrayList<MusicApplyLogItemModel>)
    fun backApplyGoing()

    interface MusicApplyLogRv{
        fun logItemClickTrue(model: MusicApplyLogItemModel)
        fun logItemClickFalse(model: MusicApplyLogItemModel)
    }
}