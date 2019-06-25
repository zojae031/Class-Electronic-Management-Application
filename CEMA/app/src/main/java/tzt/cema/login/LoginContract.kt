package tzt.cema.login

import tzt.cema.dto.User


interface LoginContract{
    interface View{
        fun alertToast(text:String)
        fun startActivity(user:User)
    }
    interface Presenter{
        fun loginBtnClick(id:String,pw:String)
    }
}