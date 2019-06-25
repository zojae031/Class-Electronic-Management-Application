package tzt.cema

import android.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import tzt.cema.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),MainContract.View {
    private val binding : ActivityMainBinding by lazy { setContentView<ActivityMainBinding>(this, R.layout.activity_main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
