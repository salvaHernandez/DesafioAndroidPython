package com.example.desafio11

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.desafio11.MainActivity.Companion.fragmentNum

class MiFragment() : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        if (fragmentNum == 1) {
            val view = inflater?.inflate(R.layout.fragment_encuesta,container,false)
            return view
        } else {
            val view = inflater?.inflate(R.layout.fragment_usuario,container,false)
            return view
        }
    }
}