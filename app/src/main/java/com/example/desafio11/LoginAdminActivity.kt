package com.example.desafio11


import Adaptador.RecyclerUsuarios
import Api.ServiceBuilder
import Api.UserApi
import Model.Usuario
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio11.MainActivity.Companion.fragmentNum
import kotlinx.android.synthetic.main.activity_login_admin.*
import kotlinx.android.synthetic.main.activity_prueba.*
import kotlinx.android.synthetic.main.fragment_usuario.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginAdminActivity : AppCompatActivity() {
    companion object {
        lateinit var con : AppCompatActivity
        lateinit var miAdapterUsuario: RecyclerUsuarios
        lateinit var listaUsuarios:ArrayList<Usuario>
    }

    lateinit var us : Usuario





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_admin)


        con = this
        listaUsuarios = arrayListOf()

        us = (intent.getSerializableExtra("obj")) as Usuario
        // A partir de aquí


    }


    fun fragmentEncuesta(view: View) {
        val fragment = MiFragment()
        replaceFragment(fragment)
        fragmentNum = 1
    }

    fun fragmentUsuario(view: View) {
        callUsuarios()
        val fragment = MiFragment()
        replaceFragment(fragment)
        fragmentNum = 2

        // Da fallo
        recyclerUsuarios.setHasFixedSize(true)
        recyclerUsuarios.layoutManager = LinearLayoutManager(this)
        miAdapterUsuario = RecyclerUsuarios(this, listaUsuarios)
        recyclerUsuarios.adapter = miAdapterUsuario
    }


    private fun replaceFragment(fragment: MiFragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()



        // no da fallo pero no carga el recycler
        // mensaje en rojo E/RecyclerView: No adapter attached; skipping layout
        if (fragmentNum == 2) {
            recyclerUsuarios.setHasFixedSize(true)
            recyclerUsuarios.layoutManager = LinearLayoutManager(this)
            miAdapterUsuario = RecyclerUsuarios(this, listaUsuarios)
            recyclerUsuarios.adapter = miAdapterUsuario

        }


    }


    fun callUsuarios () {
        val request = ServiceBuilder.buildService(UserApi::class.java)
        val call = request.getListaUsuarios()

        call.enqueue(object : Callback<ArrayList<Usuario>> {
            override fun onResponse(
                call: Call<ArrayList<Usuario>>,
                response: Response<ArrayList<Usuario>>
            ) {
                Log.e ("Salva", response.code().toString())

                if (response.code() == 200) {
                    val post=response.body()!!
                    if (post != null) {
                        for (i in 0..post.size -1) {
                            listaUsuarios.add(post[i])
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<Usuario>>, t: Throwable) {
                Log.e("Salva", "Algo ha fallado en la conexión.")
                Toast.makeText(this@LoginAdminActivity, "Algo ha fallado en la conexión.", Toast.LENGTH_LONG).show()
            }

        })

    }


}