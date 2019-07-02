package un.tpi.carpoolun.model

import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("id")
    val id: Int? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("email")
    val email: String? = null

    @SerializedName("password")
    val password: String? = null
}