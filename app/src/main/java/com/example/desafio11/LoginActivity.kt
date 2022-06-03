package com.example.desafio11

import Api.ServiceBuilder
import Api.UserApi
import Model.Encuesta
import Model.Usuario
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var us : Usuario
    lateinit var txtNombre : TextView
    lateinit var rdPel : RadioGroup
    lateinit var rdPelPref : RadioGroup
    lateinit var rdGenero : RadioGroup
    lateinit var swNumPelis : SeekBar
    lateinit var anime : Switch
    lateinit var swValoracion : SeekBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        us = (intent.getSerializableExtra("obj")) as Usuario

        txtNombre.text = us.Nombre

        swNumPelis.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                var n = seekBar.progress.toString()
                txtNumeroPeliculas.text = n

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })


        swValoracion.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                var s = seekBar.progress.toString()
                txtValorEncuesta.text = s

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })



    }

    fun Salir (view: View) {

    }

    fun EnviarEncuesta (view: View) {

        val enc = rellenaDatosEncuesta()

        val request = ServiceBuilder.buildService(UserApi::class.java)
        val call = request.addEncuesta(enc)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.code() == 200) {
                    Toast.makeText(
                        this@LoginActivity,
                        "La encuesta se ha enviado correctamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    vaciarEncuesta()
                } else Toast.makeText(
                    this@LoginActivity,
                    "Recuerda que tienes que rellenar todos los datos",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    this@LoginActivity,
                    "${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })


    }

    private fun vaciarEncuesta() {
        rdBtnPeli.isChecked = false
        rdBtnSerie.isChecked = false
        rdBtnAmbas.isChecked = false
        rdBtnAnillo.isChecked = false
        rdBtnMarvel.isChecked = false
        rdBtnMision.isChecked = false
        chkAccion.isChecked = false
        chkCienciaFiccion.isChecked = false
        chkRomanticas.isChecked = false
        swAnime.isEnabled = false
    }


    private fun rellenaDatosEncuesta():Encuesta {

        val numeroPelis = Integer.parseInt(txtNumeroPeliculas.text.toString())
        val valorEnc = Integer.parseInt(txtValorEncuesta.text.toString())

        val enc = Encuesta(1,"","","",numeroPelis,1,valorEnc,us.idUser)

        when (rdGroupPelisSeries.checkedRadioButtonId) {
            R.id.rdBtnPeli -> enc.seriePeli = "Peliculas"
            R.id.rdBtnSerie -> enc.seriePeli = "Series"
            R.id.rdBtnAmbas -> enc.seriePeli = "Ambas"
        }

        when (rdGroupSaga.checkedRadioButtonId) {
            R.id.rdBtnAnillo -> enc.saga = "Señor de los Anillos"
            R.id.rdBtnMarvel -> enc.saga = "Marvel"
            R.id.rdBtnMision -> enc.saga = "Mision imposible"
        }

        if (chkAccion.isChecked) {
            enc.generoPreferido += "Accion"
        }
        if (chkCienciaFiccion.isChecked) {
            enc.generoPreferido += "Ciencia Ficción "
        }
        if (chkRomanticas.isChecked) {
            enc.generoPreferido += "Romanticas"
        }

        if (swAnime.isEnabled) {
            enc.anime = 1
        } else {
            enc.anime = 0
        }

        return enc

    }

}