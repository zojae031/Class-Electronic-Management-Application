package tzt.cema.main

import android.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import tzt.cema.R
import tzt.cema.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {

    private val presenter = MainPresenter(this@MainActivity)

    private val binding: ActivityMainBinding by lazy {
        setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun alertToast(text: String) {
        Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
    }
    companion object{
        fun getIntent() : {

        }
    }
}
