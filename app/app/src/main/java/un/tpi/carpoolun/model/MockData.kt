package un.tpi.carpoolun.model

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
            type = Carpool.OUT_OF_UN
        )

        val carpool2 = Carpool(
            id = 2,
            driverId = 2,
            driverName = "Diego",
            capacity = 5,
            capacityLeft = 2,
            neighbourhood = "Funza",
            type = Carpool.TO_UN
        )

        val carpool3 = Carpool(
            id = 3,
            driverId = 3,
            driverName = "Felipe",
            capacity = 5,
            capacityLeft = 3,
            neighbourhood = "Funza",
            type = Carpool.OUT_OF_UN
        )

        val carpool4 = Carpool(
            id = 4,
            driverId = 4,
            driverName = "Gio",
            capacity = 5,
            capacityLeft = 3,
            neighbourhood = "Funza",
            type = Carpool.OUT_OF_UN
        )

        val carpool5 = Carpool(
            id = 5,
            driverId = 5,
            driverName = "Brayan",
            capacity = 5,
            capacityLeft = 4,
            neighbourhood = "Funza",
            type = Carpool.TO_UN
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