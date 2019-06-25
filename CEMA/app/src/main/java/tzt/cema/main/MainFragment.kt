package tzt.cema.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tzt.cema.R
import tzt.cema.databinding.FragmentBinding

class MainFragment : Fragment() {
    internal lateinit var view :FragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = DataBindingUtil.inflate(inflater,R.layout.fragment,container,false)

        return view.root
    }
}