package dsm.android.v3.ui.musicApplyLog

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class MusicApplyLogViewModelFactory(val contract: MusicApplyLogNavigator, val title: String): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(MusicApplyLogNavigator::class.java, String::class.java).newInstance(contract, title)
}