# Hillel Tomcat Demo + MealApp 

## Use [Postman Collection](./Servlets.postman_collection.json) to shoot requests on NameServlet

## App
### Java package  structure
```shell
ua.ithillel.tomcat
    ├── client            # External web service clients
    ├── dao               # Data access object classes
    ├── db                # Database utilities
    ├── exception         # Custom app exceptions
    ├── model             # Data model classes
    │   ├── dto           # Data transfer object classes - represent data from external client
    │   ├── entity        # Database entities - represent data stored in the DB
    │   ├── mapper        # Mapper classes - used to covert one model type into another
    │   └── vm            # View Models - represent data that is rendered on the UI
    ├── service           # service classes - Domain business logic of the app
    └── web               # Web UI classes: Servlets

```