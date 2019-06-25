package tzt.cema.main

import android.os.Handler
import android.os.Message
import org.jsoup.select.Elements
import tzt.cema.login.LoginContract
import tzt.cema.util.Parser

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {

    private val handler = ParsingHandler()

    override fun loginBtnClick(id: String, pw: String) {
        Parser.parse(handler,id,pw)
    }

    private inner class ParsingHandler :Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            with(msg?.data) {
                val result = this?.get("html") as Elements
                val name = result[2].text()
                val department = result[3].text()
                view.alertToast(name+department)
            }
        }
    }
}