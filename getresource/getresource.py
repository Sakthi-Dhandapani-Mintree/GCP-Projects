from flask import Flask, jsonify
from pymysql import connections, ProgrammingError
from os import environ
import json

DB_HOST = environ.get("DB_HOST")
USER = environ.get("USER")
PWD = environ.get("PASSWORD")
DATABASE = environ.get("DATABASE")

app = Flask(__name__)

dbconn = connections.Connection(
                                host=DB_HOST,
                                port=3306,
                                user=USER,
                                password=PWD,
                                db=DATABASE
                                )


@app.route("/getResource/<id>", methods=['GET'])
def getresource(id):
    result = ()
    select_sql = "SELECT `id`, `firstName`, `middleName`, `lastName`, `listOfTechWorkedOn`, `certifications`, `projects`, `applicationWorkLoadTypes` FROM `resource` WHERE `id`=%s"
    cursor = dbconn.cursor()

    try:
        cursor.execute(select_sql, (id))
        result = cursor.fetchone()

        response = {}
        response['id'] = result[0]
        response['firstName'] = result[1]
        response['middleName'] = result[2]
        response['lastName'] = result[3]
        response['listOfTechWorkedOn'] = result[4]
        response['certifications'] = result[5]
        response['projects'] = result[6]
        response['applicationWorkLoadTypes'] = result[7]

        return jsonify(response)

    except ProgrammingError as e:
        return e

    dbconn.close()


if __name__ == "__main__":
    app.run(host='127.0.0.1',port=5000, debug=True)