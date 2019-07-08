package un.tpi.carpoolun.client

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import un.tpi.carpoolun.models.user.LoggedUser
import un.tpi.carpoolun.models.user.SignInData
import un.tpi.carpoolun.models.user.NewUser
import un.tpi.carpoolun.models.user.User

const val users = "users"
const val sessions = "sessions"

interface CarpoolUNService {

    @GET(users)
    fun listUsers(): Call<List<User>>

    @POST(users)
    fun createUser(@Body user: NewUser): Call<LoggedUser>

    @POST(sessions)
    fun logIn(@Body signInData: SignInData): Call<LoggedUser>

    //@GET("Users/{user}")
    //fun listUsers(@Path("user") user: String): Call<List<User>>
}