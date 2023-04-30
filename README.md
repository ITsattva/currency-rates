Exchange Rate API

This is a RESTful API that provides exchange rate information from various sources. It includes two methods:

    Request for a list of exchange rates for all sources, with average market rates
    Request to issue a list of average exchange rates for all sources for the period

The API loads data from third-party services into an internal database, and the implemented API requests retrieve data from the local database.
API Sources

The API uses the following public APIs:

    https://api.monobank.ua/docs/
    https://minfin.com.ua/ua/developers/api/
    https://api.privatbank.ua/#p24/exchange

For each provider, a common interface and separate implementations have been implemented.
Technologies Used

The API is built with the following technologies:

    Java/Kotlin
    Spring Boot
    Hibernate
    PostgreSQL
    GIT
    Swagger for API documentation
    Gradle as a project builder

Running the API

To run the API, follow these steps:

    Clone the repository
    Use: sudo docker-compose up
    Replace current token for minfin with your own in the application.properties file.
    Run the application with: ./gradlew bootRun
    Navigate to http://localhost:8080/swagger-ui/index.html to view the API documentation and test the endpoints

Synchronizing Data

A cron job has been implemented to regularly synchronize data with the API providers. The job can be configured in the application.properties file.
API Documentation

API requests are documented using Swagger. To view the documentation, navigate to http://localhost:8080/swagger-ui/index.html after running the application.

Endpoints

The API includes the following endpoints:

GET api/rates
Returns a list of exchange rates for all sources, with average market rates.

GET api/rates/period?from={date in format DD/MM/YYY}&to={date in format DD/MM/YYY}
Returns a list of average exchange rates for all sources for the period.
