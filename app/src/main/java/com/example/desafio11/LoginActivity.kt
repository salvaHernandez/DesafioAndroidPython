package com.example.desafio11

import Model.Usuario
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoginActivity : AppCompatActivity() {
    lateinit var us : Usuario



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        us = (intent.getSerializableExtra("obj")) as Usuario

        if (!us.idRol.toString().equals("1")) {

        }

    }
}