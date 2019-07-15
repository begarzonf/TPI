def row2dict(row):
  cid = row[0]
  driverId = row[1]
  driverName = row[2]
  time = row[3]
  capacity = row[4]
  capacityLeft = row[5]
  neighbourhood = row[6]
  ctype = row[7]
  fee = row[8]
  return {
    "id": cid,
    "driverId": driverId,
    "driverName": driverName,
    "time": time,
    "capacity": capacity,
    "capacityLeft": capacityLeft,
    "neighbourhood": neighbourhood,
    "type": ctype,
    "fee": fee
  }

def carpoolTime(carpool):
  return carpool["time"]

# Returns Carpool[]
def getAllCarpools(cursor):
  try:
    sql = "SELECT * FROM carpools LIMIT 100"
    cursor.execute(sql)
    results = cursor.fetchall()
    data = []
    for row in results:
      data.append(row2dict(row))
    data = sorted(data, reverse = True, key = carpoolTime)
    return data
  except Exception as e:
    return { "error": "No se pudieron obtener los datos", "exception": str(e) }

# Returns Carpool
def createCarpool(db,cursor,driverId,driverName,time,capacity,capacityLeft,neighbourhood,ctype,fee):
  try:
    sql = "INSERT INTO carpools(driverId,driverName,time,capacity,capacityLeft,neighbourhood,type,fee) VALUES (" + str(driverId) + ",\"" + driverName + "\",\"" + time + "\"," + str(capacity) + "," + str(capacityLeft) + ",\"" + neighbourhood + "\"," + str(ctype) + "," + str(fee) + ");"
    print(sql)
    cursor.execute(sql)
    db.commit()
    return {
      "id": cursor.lastrowid,
      "driverId": driverId,
      "driverName": driverName,
      "time": time,
      "capacity": capacity,
      "capacityLeft": capacityLeft,
      "neighbourhood": neighbourhood,
      "type": ctype,
      "fee": fee
    }
  except Exception as e:
    db.rollback()
    print(e)
    return { "error":"error on insert carpool", "exception": str(e) }

# Returns Carpool[]
def findCarpools(cursor, ctype, neighbourhood, time):
  try:
    sql = "SELECT * FROM carpools WHERE neighbourhood = '" + neighbourhood + "' AND type = " + str(ctype) + ";"
    cursor.execute(sql)
    results = cursor.fetchall()
    data = []
    for row in results:
      c = row2dict(row)
      ctime = c["time"]
      if ctime.startswith(time[:10]):
        if ctime <= time:
          data.append(c)
    return sorted(data, reverse = True, key = carpoolTime)
  except Exception as e:
    print(e)
    return { "error": "No se pudieron obtener los datos", "exception": str(e) }