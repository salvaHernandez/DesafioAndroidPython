package Api

import Model.Encuesta
import Model.Usuario
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface UserApi {

    @GET("usuario/{name}")
    fun getUnUsuario(@Path("name") id:String): Call<Usuario>


    @GET("listadoUsuarios")
    fun getListaUsuarios(): Call<ArrayList<Usuario>>


    @Headers("Content-Type:application/json")
    @POST("rellenarEncuesta/")
    fun addEncuesta(@Body info: Encuesta): Call<ResponseBody>


    /* Implementar
    @GET("user/{name}/{pass}")
    fun getUser(@Path("name") id:String, ("passs") i:String): Call<Usuario>
*/

    // Ejemplos
    /*
    @GET("listado")
    fun getUsuarioss(): Call<MutableList<Usuario>>

    @GET("listado/{id}")
    fun getUnUsuario(@Path("id") id:String): Call<Usuario>

    @Headers("Content-Type:application/json")
    @POST("registrar")
    fun addUsuario(@Body info: Usuario) : Call<ResponseBody> // cuando pasas valor

    @Headers("Content-Type:application/json")
    @POST("login")
    fun loginUsuario(@Body info: Usuario) : Call<MutableList<Rol>> // cuando recoges una lista

    @DELETE("borrar/{dni}")
    fun borrarUsuario(@Path("dni") id:String) : Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @PUT("modificar")
    fun modUsuario(@Body info: Usuario) : Call<ResponseBody>
     */

}