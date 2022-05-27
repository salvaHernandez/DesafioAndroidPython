package Api

import Model.Usuario
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("usuario/{name}")
    fun getUnUsuario(@Path("name") id:String): Call<Usuario>

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