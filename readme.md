## Application Description
#### The application gets notifications via rest api and from external python producer then redirects to a kafka producer.
#### Next, the application kafka listener receives the notifications and stores them in PostgresSQL

#### Example of a notification
```json
{
  "description": "A simple notification"
}
```

## How to run the application
### Build the application
```bash
$ ./gradlew clean build
```

Start the project, run the bellow command:
```bash
$ cd ./docker/
$ docker-compose up -d
```

Stop the running Docker containers and remove the app image.
```bash
$ cd ./docker/
$ docker-compose down
$ docker rmi -f $(docker images 'docker-messaging' -a -q)
```

## Check if the notifications are created
### On Linux Run
#### wait for 20 seconds in order to establish kafka connection
## Create a notification
```bash
$ curl  -X POST http://localhost:8081/api/v1/notification -H "Content-Type: application/json" -d "{\"notification\":\"A simple notification\"}'"
```
## Get all created notifications
```bash
$ curl  -X GET http://localhost:8081/api/v1/notification -H "Content-Type: application/json"
```
## Check if the notifications are created.
```bash
curl  -X GET localhost:8081/notification -H "Content-Type: application/json"
```

## The above request should return response like below
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