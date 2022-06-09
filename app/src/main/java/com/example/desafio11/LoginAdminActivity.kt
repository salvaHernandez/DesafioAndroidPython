package com.example.desafio11


import Adaptador.RecyclerEncuestas
import Adaptador.RecyclerUsuarios
import Api.ServiceBuilder
import Api.UserApi
import Model.Encuesta
import Model.Usuario
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.desafio11.MainActivity.Companion.fragmentNum
import kotlinx.android.synthetic.main.activity_login_admin.*
import kotlinx.android.synthetic.main.activity_prueba.*
import kotlinx.android.synthetic.main.fragment_usuario.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginAdminActivity : AppCompatActivity() {
    companion object {
        lateinit var con : AppCompatActivity
        lateinit var miAdapterUsuario: RecyclerUsuarios
        lateinit var miAdapterEncuesta: RecyclerEncuestas
        lateinit var listaUsu:ArrayList<Usuario>
        lateinit var listaEnc:ArrayList<Encuesta>
    }

    lateinit var us : Usuario


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_admin)

        supportActionBar?.hide()

        con = this
        listaUsu = arrayListOf()
        listaEnc = arrayListOf()

        us = (intent.getSerializableExtra("obj")) as Usuario
        // A partir de aquí

    }


    fun fragmentEncuesta(view: View) {
        floatBtnAñadir.hide()
        callEncuestas()
        fragmentNum = 1

    }


    fun fragmentUsuario(view: View) {
        floatBtnAñadir.show()
        callUsuarios()
        fragmentNum = 2

    }

    private fun replaceFragmentEnc(fragment: MiFragmentEncuesta){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }



    private fun replaceFragment(fragment: MiFragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }


    fun callUsuarios () {
        listaUsu.clear()
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
                            listaUsu.add(post[i])
                        }
                    }
                }
                val fragment = MiFragment()
                replaceFragment(fragment)

            }


            override fun onFailure(call: Call<ArrayList<Usuario>>, t: Throwable) {
                Log.e("Salva", "Algo ha fallado en la conexión.")
                Toast.makeText(this@LoginAdminActivity, "Algo ha fallado en la conexión.", Toast.LENGTH_LONG).show()
            }

        })

    }

    private fun callEncuestas() {
        listaEnc.clear()
        val request = ServiceBuilder.buildService(UserApi::class.java)
        val call = request.getListaEncuestas()

        call.enqueue(object : Callback<ArrayList<Encuesta>> {
            override fun onResponse(
                call: Call<ArrayList<Encuesta>>,
                response: Response<ArrayList<Encuesta>>
            ) {
                Log.e ("Salva", response.code().toString())

                if (response.code() == 200) {
                    val post=response.body()!!
                    if (post != null) {
                        for (i in 0..post.size -1) {
                            listaEnc.add(post[i])
                        }
                    }
                }
                val frag = MiFragmentEncuesta()
                replaceFragmentEnc(frag)

            }


            override fun onFailure(call: Call<ArrayList<Encuesta>>, t: Throwable) {
                Log.e("Salva", "Algo ha fallado en la conexión.")
                Toast.makeText(this@LoginAdminActivity, "Algo ha fallado en la conexión.", Toast.LENGTH_LONG).show()
            }

        })

    }



}