package un.tpi.carpoolun.client

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import un.tpi.carpoolun.Constants
import un.tpi.carpoolun.models.carpool.Carpool
import un.tpi.carpoolun.models.user.LoggedUser
import un.tpi.carpoolun.models.user.SignInData
import un.tpi.carpoolun.models.user.NewUser
import un.tpi.carpoolun.models.user.User


object Client {

    const val TAG = "CarpoolUN.Client"

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.SERVER_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofit.create(CarpoolUNService::class.java)

    object Users {

        fun list() {
            service.listUsers().enqueue(
                object : Callback<List<User>> {
                    override fun onFailure(call: Call<List<User>>, t: Throwable) {
                        Log.d(TAG, "Failed :C")
                        Log.d(TAG, t.message ?: "message is null")
                        Log.d(TAG, call.request().toString())
                    }

                    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                        response.body()?.forEach {
                            Log.d(TAG, "Name is: ${it.name}, email is ${it.email}, id is ${it.id}, password is ${it.password}")
                        }
                    }
                }
            )
        }

        fun create(user: NewUser, callback: Callback<LoggedUser>) {
            service.createUser(user).enqueue(callback)
        }

        fun signIn(signInData: SignInData, callback: Callback<LoggedUser>) {
            service.logIn(signInData).enqueue(callback)
        }
    }

    object Carpools {
        fun list(callback: Callback<List<Carpool>>) {
            service.listCarpools().enqueue(callback)
        }

        fun create(carpool: Carpool, callback: Callback<Carpool>) {
            service.createCarpool(carpool).enqueue(callback)
        }

        fun find(type: Int,neighbourhood: String, time: String, callback: Callback<List<Carpool>>) {
            service.findCarpools(type, neighbourhood, time).enqueue(callback)
        }
    }
}
