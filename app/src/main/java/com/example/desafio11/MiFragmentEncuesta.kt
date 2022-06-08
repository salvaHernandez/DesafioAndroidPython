package com.example.desafio11
import Adaptador.RecyclerEncuestas
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio11.LoginAdminActivity.Companion.con
import com.example.desafio11.LoginAdminActivity.Companion.listaEnc
import com.example.desafio11.LoginAdminActivity.Companion.miAdapterEncuesta
import com.example.desafio11.MainActivity.Companion.fragmentNum
import kotlinx.android.synthetic.main.dialog_resumen_encuestas.*
import kotlinx.android.synthetic.main.dialog_resumen_encuestas.view.*
import kotlinx.android.synthetic.main.fragment_encuesta.*

class MiFragmentEncuesta : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //  return super.onCreateView(inflater, container, savedInstanceState) {
        val view=inflater?.inflate(R.layout.fragment_encuesta, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (fragmentNum == 1) {
            recyclerEncuestas.setHasFixedSize(true)
            recyclerEncuestas.layoutManager = LinearLayoutManager(view.context)
            miAdapterEncuesta = RecyclerEncuestas(con, listaEnc)
            recyclerEncuestas.adapter = miAdapterEncuesta

        }

        btnGraficoEnc.setOnClickListener {

            val builder = AlertDialog.Builder(con)
            val view = layoutInflater.inflate(R.layout.dialog_resumen_encuestas,null)

            builder.setView(view) // <- se le pasa la vista creada al builder
            val dialog = builder.create() //<- se crea el dialog
            dialog.show() //<- se muestra el showdialog

            var resumen : ArrayList<Double>
            resumen = datosTotales()

            print("jaja")


            view.txtTotalPelicula.append(resumen[0].toString()+"%")
            view.txtTotalSerie.append(resumen[1].toString()+"%")
            view.txtTotalSirAnillo.append(resumen[2].toString()+"%")
            view.txtTotalMarvel.append(resumen[3].toString()+"%")
            view.txtTotalMision.append(resumen[4].toString()+"%")
            view.txtTotalAccion.append(resumen[5].toString()+"%")
            view.txtTotalCiencia.append(resumen[6].toString()+"%")
            view.txtTotalRomanticas.append(resumen[7].toString()+"%")
            view.txtTotalNumPelis.append("Media: "+resumen[8].toString())
            view.txtTotalAnime.append(resumen[9].toString()+"%")
            view.txtTotalValoracion.append("Media: "+resumen[10].toString())


            view.btnTotalSalir.setOnClickListener {
                dialog.cancel()
            }

        }
    }

    private fun datosTotales():ArrayList<Double> {

        var datos:ArrayList<Double> = arrayListOf()
        var totalEnc = listaEnc.size
        var pelis=0.0
        var series=0.0
        var ambas = 0.0
        var sir=0.0
        var marvel=0.0
        var misionImp=0.0
        var accion=0.0
        var ciencia=0.0
        var romanticas=0.0
        var numPeliculas=0.0
        var anime=0.0
        var valoracion=0.0


        for (i in 0..(listaEnc.size -1)) {
            when (listaEnc[i].seriePeli) {
                getString(R.string.encuestaPreg1Res1) -> pelis++
                getString(R.string.encuestaPreg1Res2) -> series++
                else -> ambas++
            }
            when (listaEnc[i].saga) {
                getString(R.string.encuestaPreg2Res1) -> sir++
                getString(R.string.encuestaPreg2Res2) -> marvel++
                else -> misionImp++
            }
            if (listaEnc[i].generoPreferido.contentEquals(getString(R.string.encuestaPreg3Res1))) {
                accion++
            }
            if (listaEnc[i].generoPreferido.contentEquals(getString(R.string.encuestaPreg3Res2))) {
                ciencia++
            }
            if (listaEnc[i].generoPreferido.contentEquals(getString(R.string.encuestaPreg3Res3))) {
                romanticas++
            }
            if (listaEnc[i].anime == 1) {
                anime++
            }
            numPeliculas+=listaEnc[i].numPelis!!
            valoracion+=listaEnc[i].valoracion!!
        }

        datos.add(((pelis + ambas) / totalEnc))
        datos.add(((series + ambas) / totalEnc))
        datos.add((sir / totalEnc))
        datos.add((marvel / totalEnc))
        datos.add((misionImp / totalEnc))
        datos.add((accion / totalEnc))
        datos.add((ciencia / totalEnc))
        datos.add((romanticas / totalEnc))
        datos.add((numPeliculas / totalEnc))
        datos.add((anime / totalEnc))
        datos.add((valoracion / totalEnc))

        return datos

    }




}