package tzt.cema.dto

import tzt.cema.main.MainFragment

data class PC(var ip: String = "", var state: MainFragment.State = MainFragment.State.OFF, var num: Int = -1)