package tzt.cema.main

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import tzt.cema.R

@SuppressLint("ValidFragment")
class SubFragment(private val presenter: MainPresenter) : Fragment() {

    internal lateinit var view: tzt.cema.databinding.FragmentSubBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!::view.isInitialized) {
            view = DataBindingUtil.inflate(inflater, R.layout.fragment_sub, container, false)
            view.apply {
                pc1.setOnClickListener {
                    JsonObject().apply {
                        addProperty("type", "close")
                        addProperty("class", "202")
                        val array = JsonArray()
                        val data = JsonObject()
                        data.addProperty("num", 1)
                        array.add(data)
                        addProperty("pc", array.toString())
                        presenter.sendMessage(this.toString())
                    }
                }
                pc2.setOnClickListener {
                    JsonObject().apply {
                        addProperty("type", "close")
                        addProperty("class", "202")
                        val array = JsonArray()
                        val data = JsonObject()
                        data.addProperty("num", 2)
                        array.add(data)
                        addProperty("pc", array.toString())
                        presenter.sendMessage(this.toString())
                    }
                }

            }
        }

        return view.root
    }

}

