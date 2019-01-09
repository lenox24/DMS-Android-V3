package dsm.android.v3.ui.applyGoing

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityApplyGoingBinding
import dsm.android.v3.model.ApplyGoingModel
import dsm.android.v3.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_apply_going.*
import org.jetbrains.anko.find
import org.w3c.dom.Text

class ApplyGoingActivity : DataBindingActivity<ActivityApplyGoingBinding>(), ApplyGoingContract {
    override val layoutId: Int
        get() = R.layout.activity_apply_going

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ApplyGoingViewModelFactory(this)
        binding.applyGoingViewModel = ViewModelProviders.of(this, factory).get(ApplyGoingViewModel::class.java)
        val models = arrayListOf(
            ApplyGoingModel("토요외출", "썸피시 ㄱ ㄱ ㄱ ㄱ ㄱ", 3),
            ApplyGoingModel("일요외출", "근데 버거킹 위에있는 피시방가서 4000원 넣으면 아이스티 하나 공짜인데 거기가 더 좋은거같음", 5),
            ApplyGoingModel("평일외출", "맛있는 밥 먹고 오자 선사유적지 ㄱㄱ",0)
        )
        applyGoing_apply_list_pager.adapter = ApplyPageAdapter(models)
        applyGoing_apply_list_pager.currentItem = 0
    }

    inner class ApplyPageAdapter(val models: ArrayList<ApplyGoingModel>) : PagerAdapter() {
        override fun isViewFromObject(p0: View, p1: Any): Boolean {
            return p0 == p1
        }

        override fun getCount(): Int = models.size

        override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
            container.removeView(any as View)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val layoutInflater = LayoutInflater.from(baseContext)
            val view = layoutInflater.inflate(R.layout.item_apply_going, container, false)
            view.find<TextView>(R.id.item_applyGoing_title_tv).text = models[position].week
            view.find<TextView>(R.id.item_applyGoing_explanation_tv).text = models[position].description
            view.find<TextView>(R.id.applyGoing_count_tv).text = models[position].cnt.toString()
            container.addView(view)
            return view
        }

    }
}