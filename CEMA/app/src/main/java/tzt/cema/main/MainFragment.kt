package tzt.cema.main

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.graphics.Paint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.cell.view.*
import tzt.cema.R
import tzt.cema.databinding.FragmentMainBinding


@SuppressLint("ValidFragment")
class MainFragment(private val arr: JsonArray) : Fragment() {

    internal lateinit var view: FragmentMainBinding
    lateinit var mAdapter: MyAdapter
    private lateinit var manager: GridLayoutManager
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (!::view.isInitialized) {
            view = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
            view.apply {
                mAdapter = MyAdapter()
                mAdapter.setData(arr)
                rv.adapter = mAdapter
                manager = GridLayoutManager(view.root.context, 8)
                rv.layoutManager = manager
                screen.paintFlags = Paint.UNDERLINE_TEXT_FLAG;


            }

        }

        return view.root
    }

    inner class ViewHolder(private val view: TextView, itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btn = itemView.textView

    }

    inner class MyAdapter : RecyclerView.Adapter<ViewHolder>() {
        private var arr: JsonArray? = null
        fun setData(arr: JsonArray) {
            this.arr = arr
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.cell, parent, false)
            val holder = ViewHolder(view.textView, view)
            return holder
        }

        override fun getItemCount(): Int {
            return arr!!.size()
        }

        override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
            holder.btn.text = arr!!.get(pos).toString()

        }


    }
}