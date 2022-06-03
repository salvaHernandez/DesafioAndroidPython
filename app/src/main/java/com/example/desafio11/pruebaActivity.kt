package com.example.desafio11

import Adaptador.RecyclerUsuarios
import Api.ServiceBuilder
import Api.UserApi
import Model.Usuario
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_prueba.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class pruebaActivity : AppCompatActivity() {
    lateinit var miAdapterUsuario: RecyclerUsuarios
    lateinit var miRecyclerUser : RecyclerView
    lateinit var listaUsuarios:ArrayList<Usuario>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prueba)
        listaUsuarios = arrayListOf()

        callUsuarios()


    }


    fun usuarios (view:View) {

     //   miRecyclerUser = findViewById(R.id.recyclerPrueba) as RecyclerView
        recyclerPrueba.setHasFixedSize(true)
        recyclerPrueba.layoutManager = LinearLayoutManager(this)

        miAdapterUsuario = RecyclerUsuarios(this, listaUsuarios)
        recyclerPrueba.adapter = miAdapterUsuario
    }



    fun encuestas (view:View) {

    }


    fun callUsuarios () {
        listaUsuarios.clear()

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
                Toast.makeText(this@pruebaActivity, "Algo ha fallado en la conexión.", Toast.LENGTH_LONG).show()
            }
        })
    }
}