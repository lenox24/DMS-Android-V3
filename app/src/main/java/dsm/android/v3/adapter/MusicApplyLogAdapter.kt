package dsm.android.v3.adapter

import android.graphics.Color
import android.support.v7.widget.Api17CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import dsm.android.v3.R
import dsm.android.v3.model.MusicApplyLogItemModel
import dsm.android.v3.ui.musicApplyLog.MusicApplyLogNavigator
import org.jetbrains.anko.*

class MusicApplyLogAdapter(val models: ArrayList<MusicApplyLogItemModel>, val applyGoingLogRv: MusicApplyLogNavigator.MusicApplyLogRv): RecyclerView.Adapter<MusicApplyLogAdapter.ApplyGoingLogViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ApplyGoingLogViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_music_apply_log, p0, false)
        return ApplyGoingLogViewHolder(view)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(p0: ApplyGoingLogViewHolder, p1: Int) = p0.bind(models[p1])

    inner class ApplyGoingLogViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val music = itemView.find<TextView>(R.id.musicApply_log_card_music_tv)
        val artist = itemView.find<TextView>(R.id.musicApply_log_card_artist_tv)
        val student = itemView.find<TextView>(R.id.musicApply_log_card_student_tv)
        val cardLayout = itemView.find<Api17CardView>(R.id.musicApply_log_card)
        var clicked: Boolean = false

        fun bind(model: MusicApplyLogItemModel){
            music.text = model.music
            artist.text = model.artist
//            TODO 학생 학번 이름 받아오기
//            student.text
            itemView.setOnClickListener {
                if (clicked) {
                    itemClickedTrue()
                    applyGoingLogRv.logItemClickTrue(model)
                }
                else {
                    itemClickedFalse()
                    applyGoingLogRv.logItemClickFalse(model)
                }
            }
        }

        fun itemClickedTrue(){
            clicked = false
            music.textColorResource =  R.color.colorGray700
            artist.textColorResource = R.color.colorGray600
            student.textColorResource = R.color.colorGray400
            cardLayout.backgroundResource = R.drawable.radius_dialog_white
        }

        fun itemClickedFalse(){
            clicked = true
            music.textColorResource = R.color.colorWhite
            artist.textColor = Color.parseColor("#c6eded")
            student.textColor = Color.parseColor("#8cdbdb")
            cardLayout.backgroundResource = R.drawable.radius_view_primary
        }
    }
}