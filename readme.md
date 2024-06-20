## Application Description
#### The application gets notifications via rest api and from external Python producer then redirects to a kafka broker.
#### Next, the application kafka listener receives the notifications and stores them in PostgresSQL

#### Example of a notification
```json
{
  "notification": "A simple notification"
}
```

## How to run the application
### Build the application
```bash
$ ./gradlew clean build
```

### Start the project, run the bellow command:
```bash
$ cd ./docker/
$ docker-compose up -d
```

### Stop running containers and remove the app docker images.
```bash
$ cd ./docker/
$ docker-compose down
$ docker rmi -f $(docker images 'polixis-messaging' -a -q)
$ docker rmi -f $(docker images 'bitnami/kafka' -a -q)
$ docker rmi -f $(docker images 'timescale/timescaledb' -a -q)
```

## Check if the notifications are created
### On Linux Run
#### Please wait a few seconds to establish a connection with Kafka broker.
## Create a notification via rest api. 
```bash
$ curl  -X POST http://localhost:8081/api/v1/notification -H "Content-Type: application/json" -d "{\"notification\":\"A simple notification\"}'"
```

## Additionally, you can send notification from external producer.
### if you have Python installed then you can run the bellow code, it sends 100 notifications to the broker.
### make sure if the appropriate packages are installed (e.g. confluent kafka).
```bash
$ cd ./python/
$ python3 send_kafka_message.py
```
## Get all created notifications
```bash
$ curl  -X GET http://localhost:8081/api/v1/notification -H "Content-Type: application/json"
```
## Check if the notifications are created.
```bash
curl  -X GET localhost:8081/notification -H "Content-Type: application/json"
```

## The above request should return response like below (for one record)
```json
{
  "_embedded" : {
    "notification" : [ {
      "notification" : "A simple notification",
      "createdAt" : "2024-06-19T10:53:38.750872Z",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8081/notification/1"
        },
        "notificationEntity" : {
          "href" : "http://localhost:8081/notification/1"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8081/notification?page=0&size=20"
    },
    "profile" : {
      "href" : "http://localhost:8081/profile/notification"
    }
  },
  "page" : {
    "size" : 20,
    "totalElements" : 1,
    "totalPages" : 1,
    "number" : 0
  }
}
```