package tzt.cema.main

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.gson.JsonArray
import tzt.cema.databinding.ActivityMainBinding
import tzt.cema.dto.User


class MainActivity : AppCompatActivity(), MainContract.View {

    private val presenter = MainPresenter(this@MainActivity)
    private var arr: JsonArray? = null
    private val info by lazy { intent.extras.get(USER_INFO) as User }
    private val binding: ActivityMainBinding by lazy {
        setContentView<ActivityMainBinding>(
            this, tzt.cema.R.layout.activity_main
        )
    }
    private var classInfo: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.connectServer()

        binding.run {
            tabCategory.addTab(binding.tabCategory.newTab().setText("202호"))
            tabCategory.addTab(binding.tabCategory.newTab().setText("203호"))
            tabCategory.addTab(binding.tabCategory.newTab().setText("사용자"))
            tabCategory.tabGravity = TabLayout.GRAVITY_FILL

            pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabCategory))
            tabCategory.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    pager.currentItem = tab.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {

                }

                override fun onTabReselected(tab: TabLayout.Tab) {

                }

            })

        }
        presenter.requestData("202")
        presenter.requestData("203")
        Thread.sleep(1000)

    }

    override fun success(text: String) {
        Log.e("text", text)
    }

    override fun fail() {

    }

    override fun alertToast(text: String) {
        Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        presenter.closeSocket()
        super.onDestroy()
    }


    override fun setFragmentInfo(arr: JsonArray, classInfo: String) {
        this.arr = arr
        this.classInfo = classInfo
        binding.pager.adapter = PagerAdapter(supportFragmentManager, binding.tabCategory.tabCount, info)
    }



    companion object {
        private const val USER_INFO = "USER_INFO"
        fun getIntent(context: Context?, user: User) = Intent(context, MainActivity::class.java).apply {
            putExtra(USER_INFO, user)
        }
    }

    inner class PagerAdapter(fm: FragmentManager, private val tabNum: Int, private val user: User) :
        FragmentStatePagerAdapter(fm) {
        override fun getItem(i: Int): Fragment? {
            when (i) {
                0 -> {
                    return SubFragment(presenter)
                }
                1 -> {
                    return MainFragment(arr!!, presenter,classInfo!!)
                }
                2 -> {
                    return UserFragment(user)
                }
            }
            return null
        }

        override fun getCount(): Int {
            return tabNum
        }
    }

}
