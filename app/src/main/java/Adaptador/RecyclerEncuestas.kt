package Adaptador

import Model.Encuesta
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio11.R


class RecyclerEncuestas(var context: AppCompatActivity, var encuestas:ArrayList<Encuesta>): RecyclerView.Adapter<RecyclerEncuestas.ViewHolder>() {

    override fun getItemCount(): Int {
        return this.encuestas?.size!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.encuesta_card,parent,false), context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = encuestas[position]
        holder.bind(item, context, this)
    }


    class ViewHolder(view: View, context: AppCompatActivity): RecyclerView.ViewHolder(view) {

        val infoNombre = view.findViewById<TextView>(R.id.cardNombre)
        val serie = view.findViewById<TextView>(R.id.cardSeriePeli)
        val saga = view.findViewById<TextView>(R.id.cardSaga)
        val genero = view.findViewById<TextView>(R.id.cardGenero)
        val numPelis = view.findViewById<TextView>(R.id.cardNumPelis)
        val anime = view.findViewById<TextView>(R.id.cardAnime)
        val valoracion = view.findViewById<TextView>(R.id.cardValoracion)

        fun bind(e: Encuesta, context: AppCompatActivity, adaptador:RecyclerEncuestas){

            infoNombre.append("Nombre "+e.nombreUsu)
            serie.append("Preferencia: "+e.seriePeli)
            saga.append("Saga: "+e.saga)
            genero.append("Genero: "+e.generoPreferido)
            numPelis.append("Peliculas semanales "+e.numPelis.toString())
            if (e.anime == 1) {
                anime.append("Anime: Si")
            } else anime.append("Anime: No")
            valoracion.append("Valoración: "+e.valoracion.toString())

        }
    }

}