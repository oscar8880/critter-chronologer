# Critter Chronologer Project Starter

Critter Chronologer a Software as a Service application that provides a scheduling interface for a small business that takes care of animals. This Spring Boot project allows users to create pets, owners, and employees, and then schedule events for employees to provide services for pets.

Completed as part of the Udacity Java Web Developer Nano Degree.

## Getting Started

### Installation

1. Clone or download this repository.
2. Open IntelliJ IDEA.
3. In IDEA, select `File` -> `Open` and navigate to the `critter` directory within this repository. Select that directory to open.
4. The project should open in IDEA. In the project structure, navigate to `src/main/java/com.udacity.jdnd.course3.critter`. 
5. Within that directory, click on CritterApplication.java and select `Run` -> `CritterApplication`. 
6. Open Postman.
7. Select the `Import` button.
8. Import the file found in this repository under `src/main/resource/Udacity.postman_collection.json`
9. Expand the Udacity folder in postman.
10. Use the saved requests to play around with the API (going from top to bottom gives the full flow)

## Testing

1. Navigate to `src/test/java/com.udacity.jdnd.course3.critter`.
2. Within that directory, click on `CritterFunctionalTest.java` and select `Run` -> `Run CritterFunctionalTest`.

A window should open showing you the test executions.

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - Framework providing dependency injection, web framework, data binding, resource management, transaction management, and more.
* [Google Guava](https://github.com/google/guava) - A set of core libraries used in this project for their collections utilities.
* [H2 Database Engine](https://www.h2database.com/html/main.html) - An in-memory database used in this project to run unit tests.
* [MySQL Connector/J](https://www.mysql.com/products/connector/) - JDBC Drivers to allow Java to connect to MySQL Server

## License

This project is licensed under the MIT License - see the [LICENSE.md]()
