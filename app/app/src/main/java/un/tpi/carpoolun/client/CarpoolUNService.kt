package un.tpi.carpoolun.client

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import un.tpi.carpoolun.model.LoggedUser
import un.tpi.carpoolun.model.NewUser
import un.tpi.carpoolun.model.User

const val users = "users"

interface CarpoolUNService {

    @GET(users)
    fun listUsers(): Call<List<User>>

    @POST(users)
    fun createUser(@Body user: NewUser): Call<LoggedUser>

    //@GET("Users/{user}")
    //fun listUsers(@Path("user") user: String): Call<List<User>>
}
