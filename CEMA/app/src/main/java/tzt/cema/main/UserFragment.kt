package tzt.cema.main

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import tzt.cema.R
import tzt.cema.databinding.FragmentUserBinding

import tzt.cema.dto.User

@SuppressLint("ValidFragment")
class UserFragment constructor(private val user: User) : Fragment() {
    internal lateinit var view: FragmentUserBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!::view.isInitialized) {
            view = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
            view.run {
                infomation.text = "이름 : ${user.name}\n학과 : ${user.department}"
                Glide.with(container?.context)
                    .load("https://udream.sejong.ac.kr/upload/per/${user.id}.jpg")
                    .asBitmap()
                    .into(image)
            }
        }
        return view.root
    }

}