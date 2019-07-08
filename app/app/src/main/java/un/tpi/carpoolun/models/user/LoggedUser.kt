package un.tpi.carpoolun.models.user

import com.google.gson.annotations.SerializedName

class LoggedUser(
    @SerializedName("id")       val id: Int? = null,
    @SerializedName("name")     val name : String? = null,
    @SerializedName("email")    val email: String? = null,
    @SerializedName("token")    val token: String? = null) {

    fun isGood() : Boolean {
        return id != null && name != null && email != null && token != null
    }

    override fun toString(): String {
        return "{ id: $id, name: '$name', email: '$email', token: '$token' }"
    }
}