# Requirements

Write a simple microservice (web application) to return currency data by its ISO_4217
alphabetic code. Its core functionality must be exposed only via REST endpoints. Display the
log of all requests on one UI page.

## Tehnology stack
* Java 8
* Spring Boot
* H2 In-Memory database
* Hibernate/JPA
* Gradle or Maven (Gradle is preferred)
* GIT

## API

Get currency:
```
  GET: /currencies/usd
```
Reponse
```
{
 
    "code": "USD",
    "number": "John 840",
    "exponent": 2,
    "name": "United States dollar"
}
```

## UI

```
  http://localhost:8080/log
```