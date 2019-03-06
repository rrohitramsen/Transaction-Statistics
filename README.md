# Transaction Statistics

## Requirement
We would like to have a RESTful API for our statistics. The main use case for the API is to calculate realtime statistics for the last 60 seconds of transactions.

The API needs the following endpoints:

```
POST /transactions – called every time a transaction is made. It is also the sole input of this rest API.

GET /statistics – returns the statistic based of the transactions of the last 60 seconds.
DELETE /transactions – deletes all transactions.
```


## `How to Start`
* [transaction_statistics.sh](/transaction_statistics.sh) execute this script. 
```
$ ./transaction_statistics.sh
```
* It will build the project and start the spring boot application.

```

## Swagger-UI
* After starting the application Click on [Swagger-home](http://localhost:8080/swagger-ui.html)

## Documentation of rest end points.
* Please see the swagger page for api docs.
* ![controller_swagger](/src/main/resources/images/controller_swagger.png "controller_swagger")
* ![model_swagger](/src/main/resources/images/model_swagger.png "model_swagger")

