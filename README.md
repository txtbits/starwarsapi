# Import and sort orders from CSV files into REST API

Development of an API based on REST orders with Spring Boot from imported CSV files.
These files can be processed and filtered and later downloaded through it.

# Tools and frameworks

Tools needed to build, deploy and run the project:
- **Java JDK 8**. Recognized class-based, object-oriented programming language.
- **Gradle** (included in project with Gradle Wrapper). Open-source build automation tool that is designed to be flexible enough to build almost any type of software.

The development startup has been done using the utility [Spring Initializr](https://start.spring.io/) to initialize Spring Boot projects with some selected dependencies. The technologies used in the project are:
- **Spring Boot**: Open source Java-based framework used to create a micro Services.
- **Spring Web**: Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
- **Spring Data JPA**: Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.
- **Spring Configuration Processor**: Generate metadata for developers to offer contextual help and "code completion" when working with custom configuration keys (ex.application.properties/.yml files).
- **Swagger**: Toolset to design, build, document, and use RESTful web services. Includes automated documentation, code generation and test-case generation.
- **H2 Database**: Lightweight 100% Java SQL Database Engine for fast test for development deployment.
- **Lombok**: Java annotation library which helps to reduce boilerplate code.

# Build, deploy and run
To build the project you must use gradle. You must go into the directory of each module and execute:
```
gradle clean build -x test
```

After that, you can launch each application executable:
```
java -jar build/libs/swapidb-0.0.1-SNAPSHOT.jar
```

* Access to HTML web form to request people counts by films: [http://localhost:8080](http://localhost:8080)
* Access to API Swagger Playground: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* Access to H2 Database console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
    * Database: _swapidb_
    * User Name: _sa_
    * Password: _password_

## REST methods available

* FILMS
  * **GET /films** - Get all films data.
  * **GET /films/_{id}_** - Get the data of a film by id.
* PEOPLE
  * **GET /people** - Get all people data.
  * **GET /people/_{id}_** - Get the data of a person by id.
  * **GET /people/counts-appearances** - First query: people with number of films in which it appears and their respective titles.
  * **GET /people/counts-by-film** - Second query: People with the most appearances for selected films.
* STARSHIPS
  * **GET /starships** - Get all starships data.
  * **GET /starships/_{id}_** - Get the data of a starship by id.


# Nice-To-Have
Here are some improvements that could be made in the future:
* Improve the data load from https://swapi.dev/ asynchronously.
* Fix Many to Many relation ships to avoid doing extra code in people queries. I had a mistake with the value of the @JsonProperty annotation and had to implement additional code that is not necessary.
* Fix second query adding starships. At this moment people appear who do not drive any starship.   
* Improve exception handling.
* Dockerize it.
