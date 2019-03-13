package dsm.android.v3.ui.musicApplyLog

import dsm.android.v3.util.DataBindingActivity
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import  java.util.ArrayList
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityMusicApplyLogBinding
import dsm.android.v3.ui.musicApplyLog.MusicApplyLogData.deleteDataList
import dsm.android.v3.adapter.MusicApplyLogAdapter
import dsm.android.v3.model.MusicApplyLogItemModel
import dsm.android.v3.ui.musicApply.MusicApplyModel
import kotlinx.android.synthetic.main.activity_music_apply_log.*

class MusicApplyLogActivity : DataBindingActivity<ActivityMusicApplyLogBinding>(), MusicApplyLogNavigator,
    MusicApplyLogNavigator.MusicApplyLogRv {

    override val layoutId: Int
        get() = R.layout.activity_music_apply_log

    override fun onCreate(savedInstanceState: Bundle?) {
        val title = intent.getStringExtra("title")
        super.onCreate(savedInstanceState)
        val factory =
            MusicApplyLogViewModelFactory(this, title)
        binding.vm = ViewModelProviders.of(this, factory).get(MusicApplyLogViewModel::class.java)
        binding.musicApplyApplyRecordRv.layoutManager = LinearLayoutManager(this)
        invisibleDeleteBtn()
        explainText(this, title)


    }

    fun setMusicItems(model:MusicApplyLogItemModel){

    }

    override fun logItemClickTrue(model: MusicApplyModel.MusicApplyDataModel) {
        deleteDataList.remove(model)
        if (deleteDataList.isEmpty()) {
            invisibleDeleteBtn()
        }
    }

    override fun logItemClickFalse(model: MusicApplyModel.MusicApplyDataModel) {
        deleteDataList.add(model)
        visibleDeleteBtn()
    }

    override fun setApplyList(models: ArrayList<MusicApplyModel.MusicApplyDataModel>) {
        binding.musicApplyApplyRecordRv.adapter = MusicApplyLogAdapter(models, this)
    }

    override fun backApplyGoing() = finish()

    override fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun visibleDeleteBtn() {
        binding.applyGoingApplyDeleteBtn.visibility = View.VISIBLE
    }

    fun invisibleDeleteBtn() {
        binding.applyGoingApplyDeleteBtn.visibility = View.INVISIBLE
    }

    private fun explainText(musicApplyLogActivity: MusicApplyLogActivity, title: String) {
        when (title) {
            "월요일 기상음악" -> musicApplyLogActivity.applyGoing_explanation_tv.setText(R.string.apply_music_monday_explanation)
            "화요일 기상음악" -> musicApplyLogActivity.applyGoing_explanation_tv.setText(R.string.apply_music_tuesday_explanation)
            "수요일 기상음악" -> musicApplyLogActivity.applyGoing_explanation_tv.setText(R.string.apply_music_wednesday_explanation)
            "목요일 기상음악" -> musicApplyLogActivity.applyGoing_explanation_tv.setText(R.string.apply_music_thursday_explanation)
            "금요일 기상음악" -> musicApplyLogActivity.applyGoing_explanation_tv.setText(R.string.apply_music_friday_explanation)
        }
    }

}