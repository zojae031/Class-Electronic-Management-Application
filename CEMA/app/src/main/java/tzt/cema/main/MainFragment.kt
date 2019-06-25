package tzt.cema.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tzt.cema.R
import tzt.cema.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    internal var view: FragmentMainBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (view == null) {
            view = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        }
        return view!!.root
    }
}