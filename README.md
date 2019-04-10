# SpringJpaGetRest

Spring boot REST API project using JPA, HATEOAS and Swagger.

# Setup
- Clone the project
- Change database connection config in src/main/resources/application-{AMB}.properties and regarding to the environment configure properly the application.properties file
- Install maven dependencies using IDE auto import or using the command mvn install
- Run the app using IDE or the command mvn spring-boot:run
- Browse http//localhost:8080/rest/cities

#API Doc & Sample

a) List all cities

	GET /rest/cities

b) Get specific city

	GET /rest/cities/1

c) List all countries

	GET /rest/countries

d) Get specific country

	GET /rest/countries/1
	
e) Get cities for a specific country

	GET /rest/cities?country=Brazil
