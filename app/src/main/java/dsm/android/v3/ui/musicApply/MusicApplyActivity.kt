package dsm.android.v3.ui.musicApply

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.Api17CardView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dsm.android.v3.R
import dsm.android.v3.ui.musicApplyLog.MusicApplyLogActivity
import dsm.android.v3.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_music_apply.*
import kotlinx.android.synthetic.main.item_music_apply.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import android.util.TypedValue
import dsm.android.v3.model.MusicApplySettingViewPagerModel
import dsm.android.v3.util.saveToken

@Suppress("DEPRECATION")
class MusicApplyActivity : DataBindingActivity<dsm.android.v3.databinding.ActivityMusicApplyBinding>(), MusicApplyNavigator {

    override val layoutId: Int
    get() = R.layout.activity_music_apply

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = MusicApplyViewModelFactory(this, Lifecycle.Event.ON_RESUME)
        binding.vm =  ViewModelProviders.of(this, factory).get(MusicApplyViewModel::class.java)
       }

    override fun setViewPager(mondayCount: Int, tuesdayCount: Int, wednesdayCount: Int, thursdayCount: Int, fridayCount: Int){
        val models = arrayListOf(
            MusicApplySettingViewPagerModel(getString(R.string.apply_music_monday_title), getString(R.string.apply_music_monday_explanation),mondayCount),
            MusicApplySettingViewPagerModel(getString(R.string.apply_music_tuesday_title), getString(R.string.apply_music_tuesday_explanation),tuesdayCount),
            MusicApplySettingViewPagerModel(getString(R.string.apply_music_wednesday_title), getString(R.string.apply_music_wednesday_explanation),wednesdayCount),
            MusicApplySettingViewPagerModel(getString(R.string.apply_music_thursday_title), getString(R.string.apply_music_thursday_explanation),thursdayCount),
            MusicApplySettingViewPagerModel(getString(R.string.apply_music_friday_title), getString(dsm.android.v3.R.string.apply_music_friday_explanation),fridayCount)
        )
        val margin =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (60 * 2).toFloat(), resources.displayMetrics).toInt()
        musicApply_apply_list_pager.adapter = ApplyPageAdapter(models)
        musicApply_apply_list_pager.currentItem = 0
        musicApply_apply_list_pager.pageMargin = -margin
    }

    override fun intentMusicApplyLog() = startActivity<MusicApplyLogActivity>("currentItem" to musicApply_apply_list_pager.currentItem)

    inner class ApplyPageAdapter(val models: ArrayList<MusicApplySettingViewPagerModel>) : PagerAdapter() {

        override fun isViewFromObject(p0: View, p1: Any): Boolean  = p0 == p1

        override fun getCount(): Int = models.size

        override fun destroyItem(container: ViewGroup, position: Int, any: Any) = container.removeView(any as View)

        @RequiresApi(Build.VERSION_CODES.M)
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val layoutInflater = LayoutInflater.from(baseContext)
            val view = layoutInflater.inflate(R.layout.item_music_apply, container, false)
            view.find<TextView>(R.id.item_musicApply_title_tv).text = models[position].week
            view.find<TextView>(R.id.item_musicApply_explanation_tv).text = models[position].description
            view.find<TextView>(R.id.item_musicApply_count_tv).text = models[position].num.toString()
            view.find<Api17CardView>(R.id.item_musicApply_card).setOnTouchListener { v, event ->
                when(event.action){
                    MotionEvent.ACTION_DOWN ->changeColor(view)
                    MotionEvent.ACTION_CANCEL -> {
                        originalColor(view)
                    }
                    MotionEvent.ACTION_UP -> {
                        originalColor(view)
                        intentMusicApplyLog(position)
                    }
                }
                true
            }
            container.addView(view)
            return view
        }

        fun intentMusicApplyLog(position: Int) {
            when(position){
                0 -> startActivity<MusicApplyLogActivity>("title" to "월요일 기상음악")
                1 -> startActivity<MusicApplyLogActivity>("title" to "화요일 기상음악")
                2 -> startActivity<MusicApplyLogActivity>("title" to "수요일 기상음악")
                3 -> startActivity<MusicApplyLogActivity>("title" to "목요일 기상음악")
                4 -> startActivity<MusicApplyLogActivity>("title" to "금요일 기상음악")
            }
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun changeColor(view: View){
            view.item_musicApply_card.setCardBackgroundColor(getColor(R.color.colorPrimary))
            view.item_musicApply_layout.setBackgroundColor(getColor(R.color.colorPrimary))
            view.item_musicApply_title_tv.setTextColor(getColor(R.color.colorWhite))
            view.item_musicApply_explanation_tv.setTextColor(getColor(R.color.colorWhite))
            view.item_musicApply_count_tv.setBackground(getDrawable(R.drawable.radius_circle_white))
            view.item_musicApply_count_tv.setTextColor(getColor(R.color.colorPrimary))
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun originalColor(view: View){
            view.item_musicApply_card.setCardBackgroundColor(getColor(R.color.colorWhite))
            view.item_musicApply_layout.setBackgroundColor(getColor(R.color.colorWhite))
            view.item_musicApply_title_tv.setTextColor(getColor(R.color.colorPrimary))
            view.item_musicApply_explanation_tv.setTextColor(getColor(R.color.colorGray600))
            view.item_musicApply_count_tv.setBackground(getDrawable(R.drawable.radius_circle_primary))
            view.item_musicApply_count_tv.setTextColor(getColor(R.color.colorWhite))
        }
    }
}