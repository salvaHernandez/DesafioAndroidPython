package Model

import com.google.gson.annotations.SerializedName


data class ControlEncuesta(@SerializedName("nombreEncuesta")
                    var nombreEncuesta: String? = null,

                    @SerializedName("activada")
                    var activada: Int? = null)
