# **Traffic Light Weather**
Foo Corporation has many IoT devices scattered around the country that provide information in real time of the local traffic on the roads as well as the traffic lights. Sometimes, the traffic lights are malfunctioning, and an employee needs to go out to verify the issue and fix it. However, they don’t like to work when it rains or if it’s too hot!

SERVICE HOSTNAME: https://localhost:8080

## ***API***

### # Get traffic weather information:
#### -> GET {SERVICE HOSTNAME}/repaired
- Headers : ...
- Query Parameters:
  - Without parameters will return a list of all traffic
  - With param - `?trafficId={traffic id in string formath}` returns weather data at the traffic light point.
- Body : -
- Responses :
  - `Status: 200 OK` | Body :
    - Without trafficId
      ```json
      [
        {
          "id": "String",
          "lastHeartbeat": "Long",
          "deviceLongitude": "Double",
          "deviceLatitude": "Double",
          "deviceHeight": "Integer",
          "enabled": "Boolean",
          "connected": "Boolean"
        },
        ...
      ]
      ```
    - With trafficId
      ```json
      {
        "trafficId": "String",
        "coord": {
          "longitude": "Double",
          "latitude": "Double",
          "deviceHeight": "Integer"
        }, 
        "weather": {
          "city": "String",
          "temp": "Double",
          "feelsLike": "Double",
          "tempMin": "Double",
          "tempMax": "Double",
          "pressure": "Integer",
          "humidity": "Integer",
          "visibility": "Integer",
          "windSpeed": "Double"
        }
      }
      ```
  - `Status: 400 Bad Request` | Body :

    ```json
    {
      "description": "string"
    }
    ```
  - `Status: 404 Not Found`