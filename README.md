<p align="center">
  <a ><img src="https://spring.io/images/spring-logo-9146a4d3298760c2e7e49595184e1975.svg" width="320" alt="Spring Logo" /></a>
</p>


## Description

A hands-on project using Java, Spring Boot, AWS CodePipeline, Amazon RDS MySQL and AWS Elastic Beanstalk.

After started, you need to get an Access Token sending a POST request to the resource and with body below:  

```
http://java-api-example-env.eba-v2mpdrpn.us-east-2.elasticbeanstalk.com/auth/signin
```

```json
{
    "username": "admin",
    "password": "admin"
}
```

Currently, you can send requests to the following resouces, make sure to add the token on Authorization Header: 

```
POST http://java-api-example-env.eba-v2mpdrpn.us-east-2.elasticbeanstalk.com/v1/person
```
```json
{
    "name": "Rodrigo",
    "document": "30239933001",
    "dop": "1975-03-18"
}
```

```
GET http://java-api-example-env.eba-v2mpdrpn.us-east-2.elasticbeanstalk.com/v1/person
```

```
GET http://java-api-example-env.eba-v2mpdrpn.us-east-2.elasticbeanstalk.com/v1/person/{id}
```

```
PUT http://java-api-example-env.eba-v2mpdrpn.us-east-2.elasticbeanstalk.com/v1/person/{id}
```
```json
{
    "name": "Rodrigo",
    "document": "30239933001",
    "dop": "1975-03-18"
}
```

```
DELETE http://java-api-example-env.eba-v2mpdrpn.us-east-2.elasticbeanstalk.com/v1/person/{id}
```
