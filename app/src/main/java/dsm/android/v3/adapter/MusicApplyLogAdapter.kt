package dsm.android.v3.adapter

import android.graphics.Color
import android.support.v7.widget.Api17CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import dsm.android.v3.R
import dsm.android.v3.connecter.Connecter
import dsm.android.v3.model.MusicApplyLogItemModel
import dsm.android.v3.model.MusicModel
import dsm.android.v3.ui.musicApplyLog.MusicApplyLogActivity
import dsm.android.v3.ui.musicApplyLog.MusicApplyLogData
import dsm.android.v3.ui.musicApplyLog.MusicApplyLogNavigator
import dsm.android.v3.util.getToken
import org.jetbrains.anko.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MusicApplyLogAdapter(val models: ArrayList<MusicApplyLogItemModel>, val applyGoingLogRv: MusicApplyLogNavigator.MusicApplyLogRv): RecyclerView.Adapter<MusicApplyLogAdapter.ApplyGoingLogViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ApplyGoingLogViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_music_apply_log, p0, false)
        return ApplyGoingLogViewHolder(view, MusicApplyLogActivity(), MusicApplyLogData)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(p0: ApplyGoingLogViewHolder, p1: Int) = p0.bind(models[p1])

    inner class ApplyGoingLogViewHolder(itemView: View, val musicApplyLogActivity: MusicApplyLogActivity, val musicApplyLogData : MusicApplyLogData): RecyclerView.ViewHolder(itemView){
        val music = itemView.find<TextView>(R.id.musicApply_log_card_music_tv)
        val artist = itemView.find<TextView>(R.id.musicApply_log_card_artist_tv)
        val student = itemView.find<TextView>(R.id.musicApply_log_card_student_tv)
        val cardLayout = itemView.find<Api17CardView>(R.id.musicApply_log_card)
        var clicked: Boolean = false
        val week = {
            when (this.musicApplyLogActivity.title){
                "월요일 기상음악"-> "mon"
                "화요일 기상음악"->  "tue"
                "수요일 기상음악"-> "wed"
                "목요일 기상음악"-> "thu"
                "금요일 기상음악"-> "fri"
                else -> ""
            }
        }

        fun bind(model: MusicApplyLogItemModel){
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
            Connecter.api.getMusic(getToken(itemView.context)).enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) =
                    when (response.code()) {
                        200 -> {
                            val body = response.body()!![week.toString()].asJsonObject
                            when(musicApplyLogActivity.title){
                                "월요일 기상음악"-> {
                                    musicApplyLogData.mondayItemList.add(MusicApplyLogItemModel(
                                        body.getAsJsonArray("musicName").flatten(),
                                        body.getAsJsonArray("singer").flatten(),
                                        body.getAsJsonArray("studentId").flatten()))
                                }
                                "화요일 기상음악"->  {
                                    musicApplyLogData.tuesdayItemList.add(MusicApplyLogItemModel(
                                        body.getAsJsonArray("musicName").flatten(),
                                        body.getAsJsonArray("singer").flatten(),
                                        body.getAsJsonArray("studentId").flatten()))
                                }
                                "수요일 기상음악"->  {
                                    musicApplyLogData.wednesdayItemList.add(MusicApplyLogItemModel(
                                        body.getAsJsonArray("musicName").flatten(),
                                        body.getAsJsonArray("singer").flatten(),
                                        body.getAsJsonArray("studentId").flatten()))
                                }
                                "목요일 기상음악"->  {
                                    musicApplyLogData.thursdayItemList.add(MusicApplyLogItemModel(
                                        body.getAsJsonArray("musicName").flatten(),
                                        body.getAsJsonArray("singer").flatten(),
                                        body.getAsJsonArray("studentId").flatten()))
                                }
                                "금요일 기상음악"->  {
                                    musicApplyLogData.fridayItemList.add(MusicApplyLogItemModel(
                                        body.getAsJsonArray("musicName").flatten(),
                                        body.getAsJsonArray("singer").flatten(),
                                        body.getAsJsonArray("studentId").flatten()))
                                }
                                else -> {
                                    musicApplyLogData.fridayItemList.add(MusicApplyLogItemModel(
                                        body.getAsJsonArray("신청곡이 없습니다.").flatten(),
                                        body.getAsJsonArray("눌러서 노래를 신청해주세요.").flatten(),
                                        body.getAsJsonArray("신청없음").flatten()))
                                }
                            }
                            notifyDataSetChanged()
                        }
                        else -> {
                            Log.e("아니",response.code().toString())
                            Log.e("아니",response.body().toString())
                            Log.e("아니",getToken(itemView.context))
                            music.text = "네트워크"
                            artist.text = "상태를"
                            student.text = "확인해주세요"
                            notifyDataSetChanged()
                        }
                    }
                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    music.text = "네트워크"
                    artist.text = "상태를"
                    student.text = "확인해주세요"
                    Log.e("아니","왜안돼냐고 진짜")
                    notifyDataSetChanged()
                }
            })
        }
        fun JsonArray.flatten(): String {
            val builder = StringBuilder()
            forEach {
                builder.append(it)
            }
            return if (size() != 0) {
                forEach {
                    builder.append("${it.asString}, ")
                }
                builder.delete(builder.lastIndex - 1, builder.lastIndex).toString()
            } else
                "노래가 없습니다."
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