package dsm.android.v3.ui.institutionReportDialog

import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dsm.android.v3.R
import dsm.android.v3.databinding.DialogInstitutionReportBinding
import dsm.android.v3.util.DataBindingDialogFragment
import kotlinx.android.synthetic.main.dialog_institution_report.*
import org.jetbrains.anko.support.v4.toast

class InstitutionDialogFragment: DataBindingDialogFragment<DialogInstitutionReportBinding>(), InstitutionReportContract {

    override val layoutId: Int
        get() = R.layout.dialog_institution_report

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val factory = InstitutionReportViewModelFactory(this)
        binding.institutionReportViewModel = ViewModelProviders.of(this, factory).get(InstitutionReportViewModel::class.java)
        return rootView
    }

    override fun onStart() {
        super.onStart()
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun createShortToast(text: String) = toast(text).show()

    override fun exitInstitutionReport() = dialog.dismiss()
}