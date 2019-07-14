from flask import Flask
from flask import jsonify
from flask import request
import db.initializer as initializer
import db.users as users
import db.carpools as carpools
import json
import random
import string
import mysql.connector
import time
import logging

app = Flask(__name__)

#def getMysqlConnection():

#db = mysql.connector.connect(user='gantiva', host='0.0.0.0', password='12345678', database='users')
def getMysqlConnection():
    #usersDB
    #db = mysql.connector.connect(user='root', host='192.168.99.101', port='3307', password='password', database='db')
    db =  mysql.connector.connect(user='begarzonf', host='127.0.0.1', password='password', database='db' , auth_plugin='mysql_native_password')
    return db

db = getMysqlConnection()
cursor = db.cursor()
initializer.create(cursor)

@app.route("/")
def hello():
    output_json = "Esta funcando"
    return output_json

#GET REQUEST
@app.route('/users')
def getUsers():
        query = "select *from users "
        data = users.getUsers(cursor,query)
        return jsonify(data)

@app.route('/users/<int:user_id>')
def getUser(user_id):
    query = "select *from users where user_id = "+str(user_id)
    data = users.getUsers(cursor,query)
    return jsonify(data)

#POST REQUEST
@app.route('/users', methods = ['POST'])
def createUser():
    content = request.get_json()
    app.logger.info('create user ' + str(content))
    name = content['name']
    email = content['email']
    password = content['password']
    isValid = users.validateEmail(cursor,email)
    isValid2 =  users.validateName(cursor,name)
    if isValid and isValid2:
        data = users.newUser(db,cursor,name,email,password)
        data = users.generateToken(db,cursor,data)
        app.logger.info('returns' + str(data))
        app.logger.info('=>'+str(jsonify(data)))
        return jsonify(data)
    elif not isValid2:
        return jsonify({"error": "el nombre ya existe"})
    else:

        return jsonify({"error": "el correo ya existe"})


#UPDATE REQUEST
@app.route('/users', methods = ['PUT'])
def updateUser():
    content = request.get_json()
    user_id = content['id']
    new_name = content['new_name']
    data = users.updateUser(db,cursor,user_id,new_name)
    return jsonify(data)

#DELETE REQUEST
@app.route('/users', methods = ['DELETE'])
def deleteUser():
    content = request.get_json()
    email = content['email']
    data = users.deleteUser(db,cursor,email)
    return jsonify(data)


#Token validation
@app.route('/validateToken', methods = ['POST'])
def validateToken():
    #content = request.get_json()
    #email = content['email']
    #token = content['token']
    email = request.headers.get("email")
    token = request.headers.get("token")
    data = users.validateToken(cursor,email,token)
    isUpdated = users.updateExpiration(cursor,email)
    if isUpdated:
        data["isUpdated"] = isUpdated
    else:
        data["isUpdated"] = isUpdated
    return jsonify(data)

#get tokenscontext 
@app.route('/tokens', methods = ['GET'])
def getTokens():
    data = users.getTokens(cursor)
    return jsonify(data)


#Session start
@app.route('/sessions', methods = ['POST'])
def sessionStart():
    content = request.get_json()
    email = content['email']
    password = content['password']
    flag = users.validateUser(cursor,email,password)
    if flag:
        data = users.updateToken(cursor,email)
        print(data)
        print(jsonify(data))
        return jsonify(data)
    else:
        return jsonify({"error":"bad email or password "})

@app.route('/session', methods = ['DELETE'])
def sessionDelete():
    content = request.get_json()
    email = content['email']
    isExpired = users.expireToken(cursor,email)
    if isExpired:
        return jsonify({"error":"token expirado mi prro"})
    else:
        return jsonify({"error":"token no expirado mi prro"})

@app.route('/carpools', methods = ['GET'])
def getAllCarpools():
    return jsonify(carpools.getAllCarpools(cursor))

@app.route('/carpools', methods = ['POST'])
def createCarpool():
    content = request.get_json()
    driverId = content['driverId']
    driverName = content['driverName']
    time = content['time']
    capacity = content['capacity']
    capacityLeft = content['capacityLeft']
    neighbourhood = content['neighbourhood']
    ctype = content['type']
    fee = content['fee']
    return jsonify(carpools.createCarpool(db,cursor,driverId,driverName,time,capacity,capacityLeft,neighbourhood,ctype,fee))


if __name__ == "__main__":
    app.run(debug=True,host='127.0.0.1',port = 5005)
