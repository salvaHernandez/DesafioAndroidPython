package com.example.desafio11

import Adaptador.RecyclerUsuarios
import Model.Usuario
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio11.LoginAdminActivity.Companion.con
import com.example.desafio11.LoginAdminActivity.Companion.listaUsuarios
import com.example.desafio11.LoginAdminActivity.Companion.miAdapterUsuario
import com.example.desafio11.MainActivity.Companion.fragmentNum
import kotlinx.android.synthetic.main.activity_prueba.*
import kotlinx.android.synthetic.main.fragment_usuario.*

class MiFragment() : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        if (fragmentNum == 1) {
            val view = inflater?.inflate(R.layout.fragment_encuesta,container,false)
            return view
        } else {
            val view=inflater?.inflate(R.layout.fragment_usuario, container, false)
            return view
        }
    }
}