package un.tpi.carpoolun.client

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import un.tpi.carpoolun.models.carpool.Carpool
import un.tpi.carpoolun.models.user.LoggedUser
import un.tpi.carpoolun.models.user.SignInData
import un.tpi.carpoolun.models.user.NewUser
import un.tpi.carpoolun.models.user.User

const val users = "users"
const val sessions = "sessions"
const val carpools = "carpools"

const val carpoolNeighbourhood = "neighbourhood"
const val carpoolTime = "time"
const val carpoolType = "type"

interface CarpoolUNService {

    @GET(users)
    fun listUsers(): Call<List<User>>

    @POST(users)
    fun createUser(@Body user: NewUser): Call<LoggedUser>

    @POST(sessions)
    fun logIn(@Body signInData: SignInData): Call<LoggedUser>

    @GET(carpools)
    fun listCarpools(): Call<List<Carpool>>

    @POST(carpools)
    fun createCarpool(@Body carpool: Carpool): Call<Carpool>

    @GET("$carpools/$carpoolType/{$carpoolType}/$carpoolNeighbourhood/{$carpoolNeighbourhood}/$carpoolTime/{$carpoolTime}")
    fun findCarpools(
        @Path(carpoolType) type: Int,
        @Path(carpoolNeighbourhood) neighbourhood: String,
        @Path(carpoolTime) time: String): Call<List<Carpool>>

    //@GET("Users/{user}")
    //fun listUsers(@Path("user") user: String): Call<List<User>>
}
