@file:JvmName("ApplyStayingBindingAdapter")

package dsm.android.v3.ui.applyStaying

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat.getColor
import android.support.v4.view.ViewPager
import android.support.v7.widget.Api17CardView
import android.util.Log
import android.widget.TextView
import dsm.android.v3.R
import kotlinx.android.synthetic.main.item_apply_staying.view.*
import org.jetbrains.anko.find

@BindingAdapter("selectedStatus")
fun ViewPager.setSelectedStatus(data: MutableLiveData<Int>) {
    Log.d("ApplyStayingBinding", "Page is $currentItem")
    data.value?.let {
        getChildAt(it).let { view ->
            view.find<Api17CardView>(R.id.item_applyStaying_card).setCardBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorPrimary
                )
            )
            view.find<TextView>(R.id.item_applyStaying_title_tv).setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorWhite
                )
            )
            view.find<TextView>(R.id.item_applyStaying_explanation_tv).setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorWhite
                )
            )
        }
    }

}
