package Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Encuesta(@SerializedName("idEncuesta")
                    var idEncuesta: Int? = null,

                    @SerializedName("seriePeli")
                    var seriePeli: String? = null,

                    @SerializedName("saga")
                    var saga: String? = null,

                    @SerializedName("generoPreferido")
                    var generoPreferido: String? = null,

                    @SerializedName("numPelis")
                    var numPelis: Int? = null,

                    @SerializedName("anime")
                    var anime: Int? = null,

                    @SerializedName("valoracion")
                    var valoracion: Int? = null,

                    @SerializedName("nombreUsu")
                    var nombreUsu: String? = null) : Serializable {

}