package com.example.desafio11

import Api.ServiceBuilder
import Api.UserApi
import Model.Encuesta
import Model.Usuario
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.desafio11.MainActivity.Companion.encuestaActivada
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var us : Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        if (encuestaActivada == 0) {
            estadoEncuesta(false)
        } else {
            estadoEncuesta(true)
        }

        us = (intent.getSerializableExtra("obj")) as Usuario

        txtNombre.text = us.Nombre
        rdBtnPeli.isChecked = true
        rdBtnAnillo.isChecked = true


        sBarPeliculas.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                var n = seekBar.progress.toString()
                txtNumeroPeliculas.text = n

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })


        sBarValorEncuesta.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                var s = seekBar.progress.toString()
                txtValorEncuesta.text = s

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })



    }


    fun Salir (view: View) {
        finish()
    }

    fun EnviarEncuesta (view: View) {

        if (!chkAccion.isChecked && !chkCienciaFiccion.isChecked && !chkRomanticas.isChecked) {
            Toast.makeText(this@LoginActivity, "Tienes que elegir algun genero", Toast.LENGTH_SHORT).show()
        } else {
            val enc = rellenaDatosEncuesta()
            val request = ServiceBuilder.buildService(UserApi::class.java)
            val call = request.addEncuesta(enc)
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.code() == 200) {
                        Toast.makeText(this@LoginActivity, "La encuesta se ha enviado correctamente", Toast.LENGTH_SHORT).show()
                        vaciarEncuesta()
                    } else Toast.makeText(this@LoginActivity, "Recuerda que tienes que rellenar todos los datos", Toast.LENGTH_SHORT).show()
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

    }

    private fun vaciarEncuesta() {
        rdBtnPeli.isChecked = true
        rdBtnSerie.isChecked = false
        rdBtnAmbas.isChecked = false
        rdBtnAnillo.isChecked = true
        rdBtnMarvel.isChecked = false
        rdBtnMision.isChecked = false
        chkAccion.isChecked = false
        chkCienciaFiccion.isChecked = false
        chkRomanticas.isChecked = false
        swAnime.isChecked = false
        sBarPeliculas.progress = 0
        sBarValorEncuesta.progress = 0
    }

    private fun estadoEncuesta(estado: Boolean) {
        rdBtnPeli.isEnabled = estado
        rdBtnSerie.isEnabled = estado
        rdBtnAmbas.isEnabled = estado
        rdBtnAnillo.isEnabled = estado
        rdBtnMarvel.isEnabled = estado
        rdBtnMision.isEnabled = estado
        chkAccion.isEnabled = estado
        chkCienciaFiccion.isEnabled = estado
        chkRomanticas.isEnabled = estado
        swAnime.isEnabled = estado
        sBarPeliculas.isEnabled = estado
        sBarValorEncuesta.isEnabled = estado
        btnEnviar.isEnabled = estado

        if (!estado) {
            Toast.makeText(this@LoginActivity, "La encuesta esta desactivada", Toast.LENGTH_SHORT).show()
        }
    }

    private fun rellenaDatosEncuesta():Encuesta {

        val numeroPelis = Integer.parseInt(txtNumeroPeliculas.text.toString())
        val valorEnc = Integer.parseInt(txtValorEncuesta.text.toString())

        val enc = Encuesta(1,"","","",numeroPelis,1,valorEnc,us.Nombre)

        when (rdGroupPelisSeries.checkedRadioButtonId) {
            R.id.rdBtnPeli -> enc.seriePeli = getString(R.string.encuestaPreg1Res1)
            R.id.rdBtnSerie -> enc.seriePeli = getString(R.string.encuestaPreg1Res2)
            R.id.rdBtnAmbas -> enc.seriePeli = getString(R.string.encuestaPreg1Res3)
        }

        when (rdGroupSaga.checkedRadioButtonId) {
            R.id.rdBtnAnillo -> enc.saga = getString(R.string.encuestaPreg2Res1)
            R.id.rdBtnMarvel -> enc.saga = getString(R.string.encuestaPreg2Res2)
            R.id.rdBtnMision -> enc.saga = getString(R.string.encuestaPreg2res3)
        }
/*
        if (chkAccion.isChecked) {
            enc.generoPreferido += getString(R.string.encuestaPreg3Res1)
        }
        if (chkCienciaFiccion.isChecked) {
            enc.generoPreferido += ","+getString(R.string.encuestaPreg3Res2)
        }
        if (chkRomanticas.isChecked) {
            enc.generoPreferido += ","+getString(R.string.encuestaPreg3Res3)
        }
*/

        if (chkAccion.isChecked) {
            enc.generoPreferido += getString(R.string.encuestaPreg3Res1)
            if (chkCienciaFiccion.isChecked) {
                enc.generoPreferido += ","+getString(R.string.encuestaPreg3Res2)
            }
            if (chkRomanticas.isChecked) {
                enc.generoPreferido += ","+getString(R.string.encuestaPreg3Res3)
            }
        } else {
            if (chkCienciaFiccion.isChecked) {
                enc.generoPreferido += getString(R.string.encuestaPreg3Res2)
                if (chkRomanticas.isChecked) {
                    enc.generoPreferido += ","+getString(R.string.encuestaPreg3Res3)
                }
            } else {
                if (chkRomanticas.isChecked) {
                    enc.generoPreferido += getString(R.string.encuestaPreg3Res3)
                }
            }
        }



        if (swAnime.isChecked) {
            enc.anime = 1
        } else {
            enc.anime = 0
        }

        return enc

    }

}