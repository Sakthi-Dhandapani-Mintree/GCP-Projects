import sys
import os

# sys.path.insert(1,os.path.join(os.path.abspath('.'),'venv/Lib/site-packages'))

from flask import Flask, jsonify, request
from pymysql import connections, ProgrammingError, DatabaseError, MySQLError, DataError
from os import environ

DB_HOST = environ.get("DB_HOST")
USER = environ.get("USER")
PWD = environ.get("PASSWORD")
DATABASE = environ.get("DATABASE")

app = Flask(__name__)
dbconn = connections.Connection(
                                host=DB_HOST,
                                user=USER,
                                password=PWD,
                                db=DATABASE
                                )

@app.route('/addResource', methods=['POST'])
def addresource():

    data = request.get_json()

    id = data['id']
    firstName = data['firstName']
    middleName = data['middleName']
    lastName = data['lastName']
    listOfTechWorkedOn = data['listOfTechWorkedOn']
    certifications = data['certifications']
    projects = data['projects']
    applicationWorkLoadTypes = data['applicationWorkLoadType']

    insert_sql = "INSERT INTO `resource` VALUES ( %s, %s, %s, %s, %s, %s, %s, %s)"
    # `id`, `firstName`, `middleName`, `lastName`, `listOfTechWorkedOn`, `certifications`, `projects`, `applicationWorkLoadTypes`
    cursor = dbconn.cursor()

    try:
        cursor.execute(insert_sql, (id, firstName, middleName,lastName,listOfTechWorkedOn, certifications, projects, applicationWorkLoadTypes))
        dbconn.commit()

    except ProgrammingError as p:
        return
    except DatabaseError as d:
        return d
    except MySQLError as m:
        return m
    except DataError as de:
        return de

    dbconn.close()

addresource()


if __name__ == "__main__":
    app.run(host='127.0.0.1',port=8080, debug=True)