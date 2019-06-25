package tzt.cema.login


interface LoginContract{
    interface View{
        fun alertToast(text:String)
    }
    interface Presenter{
        fun loginBtnClick(id:String,pw:String)
    }
}