package tzt.cema.main

interface MainContract {
    interface View {
        fun alertToast(text: String)
        fun success(text:String)
        fun fail()
    }

    interface Presenter {
        fun connectServer()
        fun requestData()
        fun closeSocket()
        fun sendMessage(data:String)
    }
}