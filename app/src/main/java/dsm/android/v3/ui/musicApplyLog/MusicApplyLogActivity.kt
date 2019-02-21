package dsm.android.v3.ui.musicApplyLog

import dsm.android.v3.util.DataBindingActivity
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityMusicApplyLogBinding
import dsm.android.v3.model.MusicApplyLogItemModel
import dsm.android.v3.ui.musicApplyLog.MusicApplyLogData.deleteData
import dsm.android.v3.adapter.MusicApplyLogAdapter

class MusicApplyLogActivity : DataBindingActivity<ActivityMusicApplyLogBinding>(), MusicApplyLogNavigator, MusicApplyLogNavigator.MusicApplyLogRv {

    override val layoutId: Int
        get() = R.layout.activity_music_apply_log

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory =
            MusicApplyLogViewModelFactory(this,  intent.getStringExtra("title"))
        binding.vm = ViewModelProviders.of(this, factory).get(MusicApplyLogViewModel::class.java)
        binding.musicApplyApplyRecordRv.layoutManager = LinearLayoutManager(this)
        invisibleDeleteBtn()
    }

    override fun logItemClickTrue(model: MusicApplyLogItemModel) {
        deleteData.remove(model)
        if(deleteData.isEmpty()){
            invisibleDeleteBtn()
        }
    }

    override fun logItemClickFalse(model: MusicApplyLogItemModel) {
        deleteData.add(model)
        visibleDeleteBtn()
    }


    override fun setApplyList(models: ArrayList<MusicApplyLogItemModel>) {
        binding.musicApplyApplyRecordRv.adapter = MusicApplyLogAdapter(models, this)
    }

    override fun backApplyGoing() = finish()

    fun visibleDeleteBtn() {
        binding.applyGoingApplyDeleteBtn.visibility = View.VISIBLE
    }

    fun invisibleDeleteBtn() {
        binding.applyGoingApplyDeleteBtn.visibility = View.INVISIBLE
    }
}