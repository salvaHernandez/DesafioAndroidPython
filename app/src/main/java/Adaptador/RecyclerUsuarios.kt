package Adaptador

import Model.Usuario
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio11.R
import kotlinx.android.synthetic.main.usuarios_card.*
import android.widget.*



class RecyclerUsuarios(var context: AppCompatActivity, var usuarios:ArrayList<Usuario>): RecyclerView.Adapter<RecyclerUsuarios.ViewHolder>() {

    override fun getItemCount(): Int {
        return this.usuarios?.size!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.usuarios_card,parent,false), context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = usuarios[position]
        holder.bind(item, context, this)
    }


    class ViewHolder(view: View, context: AppCompatActivity): RecyclerView.ViewHolder(view) {

        val infoUser = view.findViewById<TextView>(R.id.txtInfoUs)


        fun bind(u: Usuario, context: AppCompatActivity, adaptador:RecyclerUsuarios){


            when (u.idRol) {
                1 -> infoUser.setText("admin  Usuario: "+u.Nombre+" Contraseña: "+u.pass)
                2 -> infoUser.setText("Usuario  Usuario: "+u.Nombre+" Contraseña: "+u.pass)
            }
            // Hacer select para recoger el rol que tiene


            itemView.setOnLongClickListener(View.OnLongClickListener {
                AlertDialog.Builder(context)
                    .setTitle("Cuidado")
                    .setMessage("Quieres borrar este usuario?")
                    .setPositiveButton("Borrar") { view, _ ->

                        // Hacer el borrado

                        view.dismiss()
                        adaptador.notifyDataSetChanged()
                    }
                    .setNegativeButton(R.string.btnSalir) { view, _ ->
                        view.dismiss()
                    }
                    .setCancelable(true)
                    .create()
                    .show()

                true
            })
        }
    }

}