package tzt.cema.main

import com.google.gson.JsonArray

interface MainContract {
    interface View {
        fun alertToast(text: String)
        fun success(text: String)
        fun fail()
        fun setFragmentInfo(arr: JsonArray, classInfo: String)
    }

    interface Presenter {
        fun connectServer()
        fun requestData(classInfo: String)
        fun closeSocket()
        fun sendMessage(data: String)
        fun setAdapter(adapter: MainFragment.MyAdapter)
    }
}