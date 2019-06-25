package tzt.cema.main

import com.google.gson.JsonArray

interface MainContract {
    interface View {
        fun alertToast(text: String)
        fun success(text:String)
        fun fail()
        fun setFragmentInfo(arr : JsonArray)
    }

    interface Presenter {
        fun connectServer()
        fun requestData()
        fun closeSocket()
        fun sendMessage(data:String)
    }
}