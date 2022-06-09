package com.example.desafio11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import Api.ServiceBuilder
import Api.UserApi
import Model.ControlEncuesta
import Model.Usuario
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {
    companion object {
        var fragmentNum = 0
        var encuestaActivada = 0
    }
    lateinit var intentAdmin: Intent
    lateinit var intentUser: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        intentAdmin = Intent (this,LoginAdminActivity::class.java)
        intentUser = Intent (this,LoginActivity::class.java)
        comprobarEstadoEncuesta()


    }

    fun Salir (view: View) {
        finish()
    }

    fun Ingresar (view: View) {


        val request = ServiceBuilder.buildService(UserApi::class.java)
        val call = request.getUnUsuario(txtUser.text.toString().trim())

        call.enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                val post = response.body()
                if (post != null) {
                    val u = Usuario (
                        post.Nombre,
                        post.idRol,
                        post.pass
                    )
                    if (u.pass == txtPass.text.toString()) {

                        if (u.idRol == 1) {
                            intentAdmin.putExtra("obj",u)
                            startActivity(intentAdmin)
                        } else {
                            intentUser.putExtra("obj",u)
                            startActivity(intentUser)
                        }
                    }
                }
                else {
                    Toast.makeText(applicationContext, "No se han encontrado resultados", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun comprobarEstadoEncuesta() {
        val request = ServiceBuilder.buildService(UserApi::class.java)
        val call = request.getEstadoEncuesta("Cine")

        call.enqueue(object : Callback<ControlEncuesta> {
            override fun onResponse(call: Call<ControlEncuesta>, response: Response<ControlEncuesta>) {
                val post = response.body()
                if (post != null) {
                    encuestaActivada = post.activada!!
                }
                else {
                    Toast.makeText(applicationContext, "No se han encontrado resultados", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ControlEncuesta>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}