## API Documentation (Swagger/OpenAPI)

This project uses [Springdoc OpenAPI](https://springdoc.org/) to automatically generate interactive API documentation.

### How to Use

1. **Build and run the application** as usual:
   ```
   .\mvnw.cmd clean package
   .\mvnw.cmd spring-boot:run
   ```
2. **Open your browser and visit:**
   - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
   - or [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

You will see an interactive UI to explore and test your REST API endpoints.

No extra configuration is needed for basic usage. You can add OpenAPI annotations to your controllers and models for richer documentation.
