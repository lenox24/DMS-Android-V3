package dsm.android.v3.ui.musicApply

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class MusicApplyViewModelFactory(val contract: MusicApplyNavigator): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(MusicApplyNavigator::class.java).newInstance(contract)
}
