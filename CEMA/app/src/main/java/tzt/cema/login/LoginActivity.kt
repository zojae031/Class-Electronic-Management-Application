package tzt.cema.main

import android.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import tzt.cema.R
import tzt.cema.databinding.ActivityLoginBinding
import tzt.cema.dto.User
import tzt.cema.login.LoginContract

class LoginActivity : AppCompatActivity(), LoginContract.View {


    private val presenter = LoginPresenter(this@LoginActivity)

    private val binding: ActivityLoginBinding by lazy {
        setContentView<ActivityLoginBinding>(
            this,
            R.layout.activity_login
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.login.setOnClickListener {
            presenter.loginBtnClick(binding.id.text.toString(), binding.pw.text.toString())
        }
    }

    override fun startActivity(user: User) {
        startActivity(MainActivity.getIntent(this@LoginActivity, user))
    }

    override fun alertToast(text: String) {
        Toast.makeText(this@LoginActivity, text, Toast.LENGTH_SHORT).show()
    }
}
