package dsm.android.v3.ui.signIn

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivitySignInBinding
import dsm.android.v3.util.DataBindingActivity
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import dsm.android.v3.adapter.EditTextBindingAdapter
import dsm.android.v3.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.startActivity
import java.util.regex.Matcher

class SignInActivity : DataBindingActivity<ActivitySignInBinding>(), SignInNavigator {
    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override val layoutId: Int
        get() = R.layout.activity_sign_in

    private val factory = SignInViewModelFactory(this)
    private val viewModel: SignInViewModel by lazy {
        ViewModelProviders.of(this, factory).get(SignInViewModel::class.java)
    }

    @SuppressLint("ResourceType", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        val slideUp = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.slide_up
        )

        val signInConstBack = findViewById<ConstraintLayout>(R.id.signIn_constraintLayout_layout)
        signInConstBack.startAnimation(slideUp)

        signIn_id_et.setOnFocusChangeListener { v, hasFocus ->
            signIn_id_tv.clicked()
            signIn_pw_tv.unClicked()
            signIn_id_et.hint = ""
            signIn_pw_et.setHint(R.string.pw_et)
        }
        signIn_pw_et.setOnFocusChangeListener { v, hasFocus ->
            signIn_id_tv.unClicked()
            signIn_pw_tv.clicked()
            signIn_id_et.setHint(R.string.id_et)
            signIn_pw_et.hint = ""
        }


    }

    override fun intentToRegister() {
        startActivity<RegisterActivity>()
    }

//    override fun confirmEditText(v: View) {
//        when(v){
//            signIn_id_et -> {
//                Log.d("SignInViewModel", "Confirm2")
//                signIn_id_tv.clicked()
//                signIn_pw_tv.unClicked()
//                signIn_id_et.hint = ""
//                signIn_pw_et.setHint(R.string.pw_et)
//            }
//            signIn_pw_et -> {
//                signIn_id_tv.unClicked()
//                signIn_pw_tv.clicked()
//                signIn_id_et.setHint(R.string.id_et)
//                signIn_pw_et.hint = ""
//            }
//        }
//    }

    fun TextView.clicked() = setTextColor(ContextCompat.getColor(this@SignInActivity, R.color.colorPrimary))
    fun TextView.unClicked() = setTextColor(ContextCompat.getColor(this@SignInActivity, R.color.colorTvUnCliked))

}