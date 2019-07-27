package tzt.cema.main

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.databinding.DataBindingUtil
import android.graphics.Color
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
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.cell.view.*
import tzt.cema.R
import tzt.cema.databinding.FragmentMainBinding
import tzt.cema.dto.PC


@SuppressLint("ValidFragment")
class MainFragment(private val arr: JsonArray, private val presenter: MainPresenter, private val classInfo: String) :
    Fragment() {

    internal lateinit var view: FragmentMainBinding
    private lateinit var mAdapter: MyAdapter
    private lateinit var manager: GridLayoutManager
    private var pcState = MutableList(40) { PC() }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (!::view.isInitialized) {

            view = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
            view.apply {
                mAdapter = MyAdapter()
                mAdapter.setData(arr)
                rv.adapter = mAdapter
                manager = GridLayoutManager(view.root.context, 9)
                rv.layoutManager = manager
                screen.paintFlags = Paint.UNDERLINE_TEXT_FLAG

                exit.setOnClickListener {
                    val numArr = MutableList(40) { -1 }
                    var idx = 0
                    for (i in pcState) {
                        if (i.state == State.SELECT) {
                            numArr.add(idx++, i.num)
                        }
                    }
                    JsonObject().apply {
                        addProperty("type", "close")
                        addProperty("class", classInfo)
                        val array = JsonArray()
                        for (i in 0 until idx) {
                            val data = JsonObject()
                            data.addProperty("num", numArr[i])
                            array.add(data)
                        }

                        addProperty("pc", array.toString())
                        presenter.sendMessage(this.toString())
                    }

                }

                all.setOnClickListener {
                    for (i in 0 until 40) {
                        if (pcState[i].state == State.ON) {
                            pcState[i].state = State.SELECT
                        }
                    }
                    mAdapter.notifyDataSetChanged()
                }
                presenter.setAdapter(mAdapter)

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
            val state = arr!!.get(pos).asJsonObject.get("flag").asBoolean
            pcState[pos].ip = arr!!.get(pos).asJsonObject.get("ip").asString
            pcState[pos].num = pos + 1
            holder.btn.run {
                text = pcState[pos].num.toString()
                if (pcState[pos].state != State.SELECT) {
                    if (state) {
                        pcState[pos].state = State.ON
                    } else {
                        pcState[pos].state = State.OFF
                    }
                }
                setOnClickListener {
                    if (pcState[pos].state == State.ON) {
                        pcState[pos].state = State.SELECT
                    } else if (pcState[pos].state == State.SELECT) {
                        pcState[pos].state = State.ON
                    }
                    notifyDataSetChanged()
                }

                setOnLongClickListener {
                    AlertDialog.Builder(context).apply {
                        setTitle("PC 정보")
                        setMessage("PC Number : ${pos + 1}\nIP : ${pcState[pos].ip}\nState : $state")
                        setPositiveButton("확인") { _, _ -> }

                        show()
                    }
                    true
                }


                when (pcState[pos].state) {
                    State.ON -> {
                        setBackgroundColor(Color.parseColor("#00DD00"))
                    }
                    State.OFF -> {
                        setBackgroundColor(Color.GRAY)
                    }
                    State.SELECT -> {
                        setBackgroundColor(Color.parseColor("#008800"))
                    }
                }


            }


        }


    }

    enum class State {
        ON, OFF, SELECT
    }
}