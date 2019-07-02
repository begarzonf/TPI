package un.tpi.carpoolun

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import un.tpi.carpoolun.model.User


interface CarpoolUNService {

    @GET("users")
    fun listUsers(): Call<List<User>>

    //@GET("Users/{user}")
    //fun listUsers(@Path("user") user: String): Call<List<User>>
}
