
## Drones Service REST API

---
### How to build

#### Requirements

- Java 11
- Java IDE (Intellij IDEA)
- MYSQL databse
- Postman(For testing ) 

---

##Building

import project drones and build and execute that since your IDE, you will need a database connection and configurate application.propertes depending your credentials


### Testing  API

the ContentType is application/json

----
--Register a drone:  http://localhost:4444/rest/drone/register POST method
The payload should be in json format like this

{

    "serialNumber":"DR123456",
    "model":"Lightweight",
    "weightLimit":"345.7",
    "battery": 0.195,
    "state":"IDLE"
}

{

    "serialNumber":"DR123457",
    "model":"Middleweight",
    "weightLimit":"456.7",
    "battery": 0.789,
    "state":"IDLE"
}

The response should be 

{
    "result": "success",
    "serialNumber": "DR123456",
    "message": "New Drone created successfully",
    "timestamp": "2023-03-12T19:53:42.67245"
}

---

-- Checking availables drones


Before loading a drone with Medication you can first check the available drones to confirm that the drone is not in use

http://localhost:4444/rest/drone/available   GET method

the response should be

{
    "status": "status",
    "timestamp": "2023-03-12T20:27:20.929622",
    "drones": [
        {
            "serialNumber": "DR123456",
            "model": "Lightweight",
            "weightLimit": 345.7,
            "battery": 0.68,
            "state": "IDLE"
        }
    ]
}

---

---Load a drone with medication items 
 
http://localhost:4444/rest/drone/load   POST method

The payload will have the following fields

- serialNumber is the unique serial for the drone being loaded
- code id the unique code for the medication load being loaded to the drone
- source is the loading point
- destination is where the load is being taken

the Medication items to be loaded for testing are code : **DR111241, DR111242, DR111243, DR111244, DR111245**

the destination and the source are any places

 - The serialNumber is the unique serialNumber a drone that you register

{

    "serialNumber":"DR123456",
    "code":"DR111241",
    "source":"Bogota",
    "destination":"Medellin"
    
}

the rsponse should be

{
    "result": "success",
    "serialNumber": "DR123456",
    "message": "Drone Loaded successfully",
    "timestamp": "2023-03-12T20:30:37.139022"
}

---

--checking load medication for a drone

http://localhost:4444/rest/drone/details/DR123457  GET method

- Check which medication item is loaded to a specific drone.


{
    "result": "success",
    "serialNumber": "DR123457",
    "timestamp": "2023-03-12T20:37:41.857891",
    "medication": {
        "code": "DR111241",
        "name": "Covax",
        "weight": 100.0,
        "image": "sade23Rd"
    }
}

---

--check the battery level for a drone

http://localhost:4444/rest/drone/battery   POST method

only you need send teh serial number

{

    "serialNumber":"DR123456"
    
    
}

the response you should be

{
    "status": "success",
    "serialNumber": "DR123456",
    "battery": "68%",
    "timestamp": "2023-03-12T20:39:11.989972"
}



---

-- delivery items

http://localhost:4444/rest/drone/deliver  POST method

you need send the serial number to find the drone delivery with the medications items


{

    "serialNumber":"DR123456"
    
    
}


the response you should be

{
    "result": "success",
    "serialNumber": "DR123456",
    "message": "Delivered successfully",
    "timestamp": "2023-03-12T20:40:46.342998"
}


