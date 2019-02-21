package dsm.android.v3.ui.musicApplyDoc

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class MusicApplyDocViewModelFactory(val contract: MusicApplyDocNavigator, val currentItem: Int): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(MusicApplyDocNavigator::class.java, Int::class.java).newInstance(contract, currentItem)
}