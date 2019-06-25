package tzt.cema.main

import android.util.Log
import com.google.gson.JsonObject
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
                { view.success(it) }
                , { view.fail() }
            )

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