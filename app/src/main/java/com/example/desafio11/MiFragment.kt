package com.example.desafio11

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class MiFragment(var n: Int) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        if (n == 1) {
            val view = inflater?.inflate(R.layout.fragment_encuesta,container,false)
        } else {
            val view = inflater?.inflate(R.layout.fragment_usuario,container,false)
        }
        return view
    }
}