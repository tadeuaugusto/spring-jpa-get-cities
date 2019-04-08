# SpringJpaGetRest


Maven Spring Boot project with Data JPA and Data Rest. Besides the starters we have also have: 
- MySQL and H2 (in-memory database) for datasources configuration
- Hypermedia as the Engine of Application State (HATEOAS)
- Swagger2 for endpoints documentation 

Available Endpoints:

- curl -X GET http://localhost:8080/rest/countries/
- curl -X GET http://localhost:8080/rest/countries/{id}
- curl -X GET http://localhost:8080/rest/cities/
- curl -X GET http://localhost:8080/rest/cities/{id}
- curl -X GET http://localhost:8080/rest/cities?country={name}



