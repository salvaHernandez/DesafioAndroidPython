package Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Usuario(@SerializedName("idUser")
                   var idUser: Int? = null,

                   @SerializedName("idRol")
                   var idRol: Int? = null,

                   @SerializedName("nombre")
                   var Nombre: String? = null,

                   @SerializedName("pass")
                   var pass: String? = null) : Serializable {

}