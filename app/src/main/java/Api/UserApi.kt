package Api

import Model.ControlEncuesta
import Model.Encuesta
import Model.Usuario
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface UserApi {

    @GET("usuario/{name}")
    fun getUnUsuario(@Path("name") id:String): Call<Usuario>


    @GET("listaUsuarios")
    fun getListaUsuarios(): Call<ArrayList<Usuario>>

    @GET("listaEncuestas")
    fun getListaEncuestas(): Call<ArrayList<Encuesta>>

    @Headers("Content-Type:application/json")
    @POST("addEncuesta")
    fun addEncuesta(@Body info: Encuesta): Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("addUser")
    fun addUsuario(@Body info: Usuario): Call<ResponseBody>


    @DELETE("reiniciarEncuesta")
    fun deleteEncuesta(): Call<ResponseBody>

    @GET("encuesta/{nombreEncuesta}")
    fun getEstadoEncuesta(@Path("nombreEncuesta") nombreEncuesta:String): Call<ControlEncuesta>


    @Headers("Content-Type:application/json")
    @PUT("activarDesactivarEnc/")
    fun modEstadoEncuesta(@Body info: ControlEncuesta): Call<ResponseBody>


}