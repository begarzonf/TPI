package un.tpi.carpoolun.models.carpool

import com.google.gson.annotations.SerializedName
import java.util.*


class Carpool(
    @SerializedName("id")            val id: Int? = null,
    @SerializedName("driverId")      val driverId : Int? = null,
    @SerializedName("driverName")    val driverName : String? = null,
    @SerializedName("time")          val time: String? = null,
    @SerializedName("capacity")      val capacity: Int? = null,
    @SerializedName("capacityLeft")  val capacityLeft: Int? = null,
    @SerializedName("neighbourhood") val neighbourhood: String? = null,
    @SerializedName("type")          val type: Int? = null,
    @SerializedName("fee")         val fee: Int? = null) {
    companion object {
        const val OUT_OF_UN = 1
        const val TO_UN = 0
    }

    fun isGood() : Boolean {
        return id != null
    }
}