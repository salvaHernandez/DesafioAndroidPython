package com.example.desafio11


import Model.Usuario
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.desafio11.MainActivity.Companion.fragmentNum

class LoginAdminActivity : AppCompatActivity() {
    lateinit var us : Usuario



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_admin)

        us = (intent.getSerializableExtra("obj")) as Usuario

    }

    fun fragmentEncuesta(view: View) {
        val fragment = MiFragment()
        replaceFragment(fragment)
        fragmentNum = 1
    }

    fun fragmentUsuario(view: View) {
        val fragment = MiFragment()
        replaceFragment(fragment)
        fragmentNum = 2

    }

    private fun replaceFragment(fragment: MiFragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }

}