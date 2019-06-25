package tzt.cema.main

import android.util.Log
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import tzt.cema.util.RxSocket

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private var disposable: Disposable? = null
    private var socket: RxSocket? = null

    override fun connectServer() {
        socket = RxSocket()
        disposable = socket!!.connect()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { Log.e("doOnSubscribe", "구독!") }
            .doOnTerminate {
                Log.e("doOnTerminate", "제거")
                closeSocket()
                disposable?.dispose()
            }
            .subscribe(
                { jsonData ->
                    val obj = JsonParser().parse(jsonData).asJsonObject
                    val ele = JsonParser().parse(obj.get("pc").asString)
                    val array = ele.asJsonArray
                    view.setFragmentInfo(array)
                    for(i in array){
                        Log.e("array : ",i.toString())
                    }

                }
                , { view.fail() }
            )



    }

    override fun requestData() {
        Thread.sleep(1000)
        JsonObject().apply {
            addProperty("type","get")
            addProperty("class","203")
            sendMessage(this.toString())
        }
    }

    override fun sendMessage(data: String) {
        socket?.sendData(data)
    }

    override fun closeSocket() {
        socket?.closeSocket()
    }
}