package un.tpi.carpoolun.models

import un.tpi.carpoolun.Constants
import un.tpi.carpoolun.models.carpool.Carpool
import java.text.SimpleDateFormat

object MockData
{
    fun carpoolList() : ArrayList<Carpool>{
        val carpool1 = Carpool(
            id = 1,
            driverId = 1,
            driverName = "Nicolas",
            capacity = 5,
            capacityLeft = 3,
            neighbourhood = "Funza",
            type = Carpool.OUT_OF_UN,
            fee = 20000,
            time = "2019-07-16 14:20"
        )

        val carpool2 = Carpool(
            id = 2,
            driverId = 2,
            driverName = "Diego",
            capacity = 5,
            capacityLeft = 2,
            neighbourhood = "Alqueria",
            type = Carpool.TO_UN,
            fee = 1500,
            time = "2019-07-16 16:20"
        )

        val carpool3 = Carpool(
            id = 3,
            driverId = 3,
            driverName = "Felipe",
            capacity = 5,
            capacityLeft = 3,
            neighbourhood = "San Cristobal Sur",
            type = Carpool.OUT_OF_UN,
            fee = 2000,
            time = "2019-07-17 9:20"
        )

        val carpool4 = Carpool(
            id = 4,
            driverId = 4,
            driverName = "Gio",
            capacity = 5,
            capacityLeft = 3,
            neighbourhood = "Las Cruces",
            type = Carpool.OUT_OF_UN,
            fee = 3400,
            time = "2019-07-17 12:00"
        )

        val carpool5 = Carpool(
            id = 5,
            driverId = 5,
            driverName = "Brayan",
            capacity = 5,
            capacityLeft = 4,
            neighbourhood = "Tintal",
            type = Carpool.TO_UN,
            fee = 1500,
            time = "2019-07-18 6:20"
        )

        val carpools = ArrayList<Carpool>()
        carpools.add(carpool1)
        carpools.add(carpool2)
        carpools.add(carpool3)
        carpools.add(carpool4)
        carpools.add(carpool5)
        return carpools
    }
}