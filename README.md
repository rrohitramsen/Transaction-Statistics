# Transaction Statistics

## Requirement
We would like to have a RESTful API for our statistics. The main use case for the API is to calculate realtime statistics for the last 60 seconds of transactions.

The API needs the following endpoints:

```
POST /transactions – called every time a transaction is made. It is also the sole input of this rest API.

GET /statistics – returns the statistic based of the transactions of the last 60 seconds.
DELETE /transactions – deletes all transactions.
```

You can complete the challenge offline using an IDE of your choice. To download the application skeleton, please enable Use Git in the editor and follow the instructions on screen. Please make sure you push your changes to the master branch and test your solution on HackerRank before submitting.
Create a simple entity management system that provides a REST endpoint to manage any entity (e.g. a product in a catalog, patient information in healthcare etc.).


## How to start ?

```
$ mvn spring-boot:run
```

## Swagger-UI
* After starting the application Click on [Swagger-home](http://localhost:8080/swagger-ui.html)

## Documentation of rest end points.
* Please see the swagger page for api docs.
* ![controller_swagger](/src/main/resources/images/controller_swagger.png "controller_swagger")
* ![model_swagger](/src/main/resources/images/model_swagger.png "model_swagger")

