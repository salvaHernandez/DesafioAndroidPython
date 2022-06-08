package com.example.desafio11

import Adaptador.RecyclerUsuarios
import Api.ServiceBuilder
import Api.UserApi
import Model.Usuario
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio11.LoginAdminActivity.Companion.con
import com.example.desafio11.LoginAdminActivity.Companion.listaUsu
import com.example.desafio11.LoginAdminActivity.Companion.miAdapterUsuario
import com.example.desafio11.MainActivity.Companion.fragmentNum
import kotlinx.android.synthetic.main.dialog_add_user.view.*
import kotlinx.android.synthetic.main.fragment_usuario.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MiFragment() : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       // return super.onCreateView(inflater, container, savedInstanceState)
            val view=inflater?.inflate(R.layout.fragment_usuario, container, false)
            return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (fragmentNum == 2) {

            recyclerUsuarios.setHasFixedSize(true)
            recyclerUsuarios.layoutManager = LinearLayoutManager(view.context)
            miAdapterUsuario= RecyclerUsuarios(con, listaUsu)
            recyclerUsuarios.adapter = miAdapterUsuario

        }

        floatBtnAñadir.setOnClickListener {

            val builder = AlertDialog.Builder(con)
            val view = layoutInflater.inflate(R.layout.dialog_add_user,null)

            builder.setView(view) // <- se le pasa la vista creada al builder
            val dialog = builder.create() //<- se crea el dialog
            dialog.show() //<- se muestra el showdialog



            view.btnRegistrar.setOnClickListener{
                var n = 2

                if (view.txtPassDLG.text.toString().trim() != "" && view.txtNombreDLG.text.toString().trim() != "") {

                    if (view.swAdmin.isChecked) {
                        n = 1
                    }
                    var us = Usuario(view.txtNombreDLG.text.toString(),n, view.txtPassDLG.text.toString())

                    if (comprobarUsuario(us)) {

                        if (insertarUsuario(us)) {
                            dialog.cancel()
                            listaUsu.add(us)
                            miAdapterUsuario= RecyclerUsuarios(con, listaUsu)
                            recyclerUsuarios.adapter = miAdapterUsuario

                        } else Toast.makeText(con, "Fallo de conexión con la BD", Toast.LENGTH_LONG).show()
                    } else  Toast.makeText(con, "El usuario registrado ya existe, inserta otro", Toast.LENGTH_LONG).show()
                } else Toast.makeText(con, "Tienes que rellenar todos los campos", Toast.LENGTH_LONG).show()
            }

            view.btnTotalSalir.setOnClickListener {
                dialog.cancel()
            }
        }

    }


    private fun comprobarUsuario(us: Usuario):Boolean {

        var resultado : Boolean
        resultado = true
        val request = ServiceBuilder.buildService(UserApi::class.java)
        val call = request.getUnUsuario(us.Nombre.toString())

        call.enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                val post = response.body()
                if (post != null) {
                    resultado = false
                } else {
                    resultado = insertarUsuario(us)
                }
            }
            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Toast.makeText(LoginAdminActivity.con, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
        return resultado
    }


    fun insertarUsuario (us : Usuario): Boolean {

        var res = false

        val request=ServiceBuilder.buildService(UserApi::class.java)
        val call = request.addUsuario(us)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.code() == 200) {
                    Toast.makeText(
                        LoginAdminActivity.con,
                        "El usuario se ha enviado correctamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    res = true
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    LoginAdminActivity.con,
                    "${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        return res
    }


}