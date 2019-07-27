package tzt.cema.util

import io.reactivex.Observable
import java.io.*
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketException
import java.nio.charset.StandardCharsets

class RxSocket {

    companion object {
        private const val IP = "192.168.0.9"
        //        private const val IP = "223.195.29.127"
//        private const val IP = "192.168.1.155"
        private const val port = 5050
    }


    private var socket: Socket? = null
    private val writer: BufferedWriter by lazy {
        BufferedWriter(
            OutputStreamWriter(
                socket?.getOutputStream(),
                StandardCharsets.UTF_8
            )
        )
    }
    private val reader: BufferedReader by lazy {
        BufferedReader(
            InputStreamReader(
                socket?.getInputStream(),
                StandardCharsets.UTF_8
            )
        )
    }
    private val out: PrintWriter by lazy { PrintWriter(writer, true) }

    fun connect(): Observable<String> = Observable.create { emitter ->
        socket = Socket()
        socket!!.connect(InetSocketAddress(IP, port), 3000)

        while (!socket!!.isClosed) {
            try {
                val data = reader.readLine() ?: "error"
                emitter.onNext(data)
            } catch (e: Exception) {
                if (e is SocketException) e.printStackTrace()
                else emitter.onError(e)
            }
        }

        emitter.onComplete()
    }

    fun sendData(data: String) {
        Thread { if (socket?.isConnected!!) out.println(data) }.start()
    }

    fun closeSocket() {
        socket?.close()
    }

}