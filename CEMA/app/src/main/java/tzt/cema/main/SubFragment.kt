package tzt.cema.main

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tzt.cema.R

@SuppressLint("ValidFragment")
class SubFragment(private val presenter: MainPresenter) :Fragment(){
    internal lateinit var view:tzt.cema.databinding.FragmentSubBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!::view.isInitialized) {
            view = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
            view.button.setOnClickListener {  }
            view.button2.setOnClickListener {  }
        }
        return view.root
    }
}