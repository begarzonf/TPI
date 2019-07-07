package un.tpi.carpoolun.model

import com.google.gson.annotations.SerializedName

class SignInData(
    @SerializedName("password")     val password : String? = null,
    @SerializedName("email")        val email: String? = null
)