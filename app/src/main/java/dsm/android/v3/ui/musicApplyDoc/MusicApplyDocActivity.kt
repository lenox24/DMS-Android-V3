package dsm.android.v3.ui.musicApplyDoc

import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AlertDialog
import dsm.android.v3.R
import dsm.android.v3.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_music_apply_doc.*
import org.jetbrains.anko.toast

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MusicApplyDocActivity: DataBindingActivity<dsm.android.v3.databinding.ActivityMusicApplyDocBinding>(), MusicApplyDocNavigator{

    override val layoutId: Int
        get() = R.layout.dialog_bug_report

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory =
            MusicApplyDocViewModelFactory(this, intent.getIntExtra("currentItem", 0))
        binding.vm = ViewModelProviders.of(this, factory).get(MusicApplyDocViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        val builder = AlertDialog.Builder(this@MusicApplyDocActivity)
        val dialog: AlertDialog = builder.create()
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun backApplyMusic() = finish()

    override fun setErrorApplyMusicName() {
        music_dialog_music_title_edit.error = "노래 제목을 입력해주세요"
    }

    override fun setErrorApplyArtist() {
        music_dialog_artist_edit.error = "아티스트를 입력해주세요"
    }

    override fun createListFullWarningToast() {
        toast("노래 신청이 마감되었습니다.")
    }
}