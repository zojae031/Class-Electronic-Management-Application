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
    private lateinit var adapter: MainFragment.MyAdapter
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
            .doOnComplete { Log.e("doOnComplete", "완료") }
            .doOnNext { Log.e("doOnNext", "next") }
            .subscribe(
                {
                    val obj = JsonParser().parse(it).asJsonObject
                    val classInfo = obj.get("class").asString
                    val ele = JsonParser().parse(obj.get("pc").asString)
                    val array = ele.asJsonArray

                    view.setFragmentInfo(array, classInfo)


                    adapter.notifyDataSetChanged()
                }
                , { view.fail() }
            )


    }

    override fun setAdapter(adapter: MainFragment.MyAdapter) {
        this.adapter = adapter
    }

    override fun requestData(classInfo: String) {
        Thread.sleep(1000)
        JsonObject().apply {
            addProperty("type", "get")
            addProperty("class", classInfo)
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