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
          "lastHeartbeat": 1234567890134,
          "deviceLongitude": -12.345678,
          "deviceLatitude": 12.345678,
          "deviceHeight": 123,
          "enabled": false,
          "connected": false
        }
      ]
      ```
    - With trafficId
      ```json
      {
        "trafficDevice": {
          "id": "String",
          "lastHeartbeat": 1234567890134,
          "deviceLongitude": -12.345678,
          "deviceLatitude": 12.345678,
          "deviceHeight": 123,
          "enabled": false,
          "connected": false
        },
        "weather": {
          "weather": [
            {
            "main": "String",
            "description": "String"
            }
          ],
          "main": {
            "temp": 12.34,
            "feels_like": 12.34,
            "temp_min": 12.34,
            "temp_max": 12.34,
            "pressure": 1234,
            "humidity": 12
          },
          "visibility": 12345,
          "wind": {
            "speed": 1.23,
            "deg": 123,
            "gust": 1.23
          },
          "name": "String"
        }
      }
      ```
  - `Status: 404 Not Found` | Body :

    ```json
    {
      "description": "String"
    }
    ```
  - `Status: 400 Bad Request`