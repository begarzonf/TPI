package un.tpi.carpoolun.models.user

import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("id")       val id: Int? = null,
    @SerializedName("name")     val name : String? = null,
    @SerializedName("email")    val email: String? = null) {
    fun isGood() : Boolean {
        return id != null && name != null && email != null
    }
}